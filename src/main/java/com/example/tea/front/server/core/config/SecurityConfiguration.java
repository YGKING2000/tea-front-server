package com.example.tea.front.server.core.config;

import com.alibaba.fastjson.JSON;
import com.example.tea.front.server.common.web.JsonResult;
import com.example.tea.front.server.common.web.ServiceCode;
import com.example.tea.front.server.core.filter.JwtAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/20 16:43
 */
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
// @EnableWebSecurity(debug = true)// 开启调试模式，在控制台将显示很多日志，在生产环境中不宜开启
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public SecurityConfiguration() {
        log.debug("创建配置类对象: SecurityConfiguration");
    }

    @Resource
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 返回此加密的编码器之后，用户输入的密码会通过此编码器加密之后再和数据库里面的密码进行比较
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置Security框架不使用Session
        // SessionCreationPolicy.NEVER:从不主动创建Session，但是Session存在的话，会自动使用
        // SessionCreationPolicy.STATELESS: 无状态，无论是否存在Session，都不使用
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 将自定义的解析JWT的过滤器添加到Security框架的过滤器链中
        // 具体位置并不严格要求，但是必须添加在检查SecurityContext的Authentication之前
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        // 允许跨域访问，本质上是启用了Security框架自带的CorsFilter
        http.cors();

        // 处理"无认证信息却访问需要认证的资源时"的响应
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            // public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
                response.setContentType("application/json;charset=utf-8");
                String message = "操作失败，你当前未登录!";
                JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED, message);
                PrintWriter writer = response.getWriter();
                writer.println(JSON.toJSONString(jsonResult));
                writer.close();
            }
        });

        String[] urls = {
                "favicon.ico",
                "/doc.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources",// API资源文件
                "/v2/api-docs",// API文档请求
                "/resources/**", // 静态资源文件夹，通常是上传的文件，请与配置文件中的"tea-store.upload.base-dir-name"一致
                "/account/users/login",//用户登录
                "/content/categories",
                "/content/tags",
                "/content/articles",
                "/content/articles/*",
                "/content/articles/list-by-category",
                "/content/comments/list-by-article"
        };
        // 禁用"防止伪造的跨域攻击的防御机制"
        http.csrf().disable();

        // 配置请求授权
        // 如果某个请求被多次配置，按照"第一匹配原则"处理
        // 应该将精确的配置写在前面，将较模糊的配置写在后面
        http.authorizeRequests()
                .mvcMatchers(urls) // 匹配某些请求
                .permitAll() // 许可，即不需要通过认证就可以访问
                .anyRequest() // 任何请求，从执行效果来看，也可以视为: 除了以上配置过的以外的其它请求
                .authenticated() // 需要通过认证才可以访问
        ;

        // 是否调用以下方法，将决定是否启用默认的登录页面
        // 当未通过认证时，如果有登录页，则自动跳转到登录，如果没有登录页，则直接响应403
        http.formLogin();

        // super.configure(http); // 不要调用父类的同名方法，许多默认的效果都是父类方法配置的
    }
}
