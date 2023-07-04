package com.example.tea.front.server.core.filter;

import com.alibaba.fastjson.JSON;
import com.example.tea.front.server.account.dao.cache.IUserCacheRepository;
import com.example.tea.front.server.common.consts.HttpConsts;
import com.example.tea.admin.server.common.pojo.po.UserLoginInfoPO;
import com.example.tea.front.server.common.security.CurrentPrincipal;
import com.example.tea.front.server.common.web.JsonResult;
import com.example.tea.front.server.common.web.ServiceCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/26 09:39
 */
@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter implements HttpConsts {

    /**
     * JWT的最小值
     */
    private static final int JWT_MIN_LENGTH = 113;

    @Value("${tea-store.jwt.secret-key}")
    private String secretKey;

    @Resource
    private IUserCacheRepository cacheRepository;

    public JwtAuthorizationFilter() {
        log.info("创建过滤器对象: JwtAuthorizationFilter");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("开始解析请求中的Jwt并存储到SecurityContext中......");

        //试接收客户端的请求中携带的JWT数据
        // 业内惯用的做法是:客户端会将JWT放在请求头中名为Authorization的属性中
        String jwt = request.getHeader(HttpConsts.HEADER_AUTHORIZATION);
        log.debug("请求中携带的JWT数据为: {}", jwt);

        // 判断JWT的基本有效性(没有必要试解析式明显错误的JWT数据)
        if (!StringUtils.hasText(jwt) || jwt.length() < JWT_MIN_LENGTH) {
            log.warn("当前请求中，客户端没有携带有效的JWT，直接放行!");
            filterChain.doFilter(request, response);
            return;
        }

        // 判断Redis中是否存在此JWT相关数据
        UserLoginInfoPO loginInfoPO = cacheRepository.getLoginInfo(jwt);
        if (loginInfoPO == null) {
            // 放行，不会向SecurityContext中存入认证信息，则相当于没有携带JWT
            log.warn("在Redis中无此JWT对应的信息，将直接放行，交由后续的组件进行处理");
            filterChain.doFilter(request, response);
            return;
        }

        // 判断此次请求，与当初登录成功时的相关信息是否相同
        String ip = loginInfoPO.getIp();
        String userAgent = loginInfoPO.getUserAgent();
        if (!ip.equals(request.getRemoteAddr()) && !userAgent.equals(request.getHeader(HEADER_USER_AGENT))) {
            // 本次请求的信息与当初登录时完全不同，则视为无效的JWT
            log.warn("本次请求的信息与当初登录时完全不同，将直接放行，交由后续的组件进行处理");
            filterChain.doFilter(request, response);
            return;
        }


        // 尝试解析JWT数据
        log.debug("尝试解析JWT数据……");
        response.setContentType("application/json;charset=utf-8");
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException e) {
            log.warn("解析JWT时出现异常: ExpiredJwtException");
            String message = "操作失败，你的登录信息已过期，请重新登录!";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_JWT_EXPIRED, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        } catch (SignatureException e) {
            log.warn("解析JWT时出现异常: SignatureException");
            String message = "非法访问，你的本次操作已被记录!";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_JWT_SIGNATURE, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        } catch (MalformedJwtException e) {
            log.warn("解析JWT时出现异常: MalformedJwtException");
            String message = "非法访问，你的本次操作已被记录!";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_JWT_MALFORMED, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        } catch (Throwable e) {
            log.warn("解析JWT时出现异常: Throwable");
            log.debug("异常跟踪信息如下: ", e);
            String message = "服务器忙，请稍后再试!";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_JWT_MALFORMED, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        }

        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);
        // String authoritiesJsonString = claims.get("authoritiesJsonString", String.class);
        String authoritiesJsonString = loginInfoPO.getAuthoritiesJsonString();

        log.debug("当前请求中JWT携带的用户id: {}", id);
        log.debug("当前请求中JWT携带的用户名username: {}", username);
        log.debug("当前请求中JWT携带的用户权限列表authorities: {}", authoritiesJsonString);

        Integer enable = cacheRepository.getEnableByUserId(id);
        if (enable != 1) {
            log.warn("用户已被禁用!");
            String message = "用户已被禁用!";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED_DISABLED, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        }

        // 将解析得到的用户数据创建为Authentication对象
        CurrentPrincipal principal = new CurrentPrincipal();
        principal.setId(id);
        principal.setUsername(username);
        Object credentials = null;
        List<SimpleGrantedAuthority> authorities = JSON.parseArray(authoritiesJsonString, SimpleGrantedAuthority.class);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);

        // Authentication对象存入到SecurityContext中
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // 过滤器链继续执行
        filterChain.doFilter(request, response);
    }
}
