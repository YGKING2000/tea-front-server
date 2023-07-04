package com.example.tea.front.server.account.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.tea.front.server.account.dao.cache.IUserCacheRepository;
import com.example.tea.front.server.account.dao.persist.repository.IUserRepository;
import com.example.tea.front.server.account.dao.persist.repository.IUserRoleRepository;
import com.example.tea.front.server.account.pojo.entity.User;
import com.example.tea.front.server.account.pojo.entity.UserRole;
import com.example.tea.front.server.account.pojo.param.UserAddNewParam;
import com.example.tea.front.server.account.pojo.param.UserLoginInfoParam;
import com.example.tea.front.server.account.pojo.vo.UserListItemVO;
import com.example.tea.front.server.account.pojo.vo.UserLoginResultVO;
import com.example.tea.front.server.account.pojo.vo.UserStandardVO;
import com.example.tea.front.server.account.security.CustomUserDetails;
import com.example.tea.front.server.account.service.IUserService;
import com.example.tea.front.server.common.exception.ServiceException;
import com.example.tea.admin.server.common.pojo.po.UserLoginInfoPO;
import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.common.web.ServiceCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 处理用户数据的业务实现类
 *
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @date 2023/06/13 17:52
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;

    @Value("${tea-store.jwt.secret-key}")
    private String secretKey;

    @Value("${tea-store.jwt.duration-in-minute}")
    private Long durationInMinute;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private IUserRepository userRepository;

    @Resource
    private IUserRoleRepository userRoleRepository;

    @Resource
    private IUserCacheRepository cacheRepository;

    @Resource
    private AuthenticationManager authenticationManager;

    public UserServiceImpl() {
        log.debug("创建业务类对象: UserServiceImpl");
    }

    @Override
    public UserLoginResultVO login(UserLoginInfoParam userLoginInfoParam, String ip, String userAgent) {
        log.debug("开始处理【用户登录】的业务，参数: {}", userLoginInfoParam);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userLoginInfoParam.getUsername(), userLoginInfoParam.getPassword());
        log.debug("准备调用AuthenticationManager的认证方法，判断此用户名、密码是否可以成功登录……");
        Authentication authenticateResult
                = authenticationManager.authenticate(authentication);
        log.debug("验证用户登录成功，返回的认证结果: {}", authenticateResult);

        Object principal = authenticateResult.getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        Long id = userDetails.getId();
        log.debug("从认证结果中的当事人中获取ID: {}", id);
        String username = userDetails.getUsername();
        log.debug("从认证结果中的当事人中获取用户名: {}", username);
        String avatar = userDetails.getAvatar();
        log.debug("从认证结果中的当事人中获取头像: {}", avatar);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        log.debug("从认证结果中的当事人中获取权限列表: {}", authorities);
        String authoritiesJsonString = JSON.toJSONString(authorities);
        log.debug("将权限列表对象转换为JSON格式的字符串: {}", authoritiesJsonString);

        // Claims类继承自Map<String, Object>接口
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("username", username);
        claims.put("avatar", avatar);
        // TODO 生成JWT时，不再存入权限列表
        // claims.put("authoritiesJsonString", authoritiesJsonString);

        // 注意在第一个数后加上L将结果变为long，避免超过int的最大值 👇
        Date date = new Date(System.currentTimeMillis() + durationInMinute * 60 * 1000);
        String jwt = Jwts.builder()
                .setHeaderParam("alg", "HS256")// 验证方式
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)// 存储的信息 
                .setExpiration(date)// Jwt过期的时间
                .signWith(SignatureAlgorithm.HS256, secretKey)// 验证方式+签名
                .compact();

        // TODO 生成JWT之后，需要将权限列表存入到Redis中
        UserLoginInfoPO userLoginInfoPO = new UserLoginInfoPO();
        userLoginInfoPO.setIp(ip);
        userLoginInfoPO.setUserAgent(userAgent);
        userLoginInfoPO.setAuthoritiesJsonString(authoritiesJsonString);
        cacheRepository.saveLoginInfo(jwt, userLoginInfoPO);

        cacheRepository.saveEnableByUserId(id, 1);

        return new UserLoginResultVO().setId(id).setUsername(username).setAvatar(avatar).setToken(jwt);

        // log.debug("准备将认证信息结果存入到SecurityContext中……");
        // SecurityContext securityContext = SecurityContextHolder.getContext();
        // securityContext.setAuthentication(authenticateResult);
        // log.debug("已经将认证信息存入到SecurityContext中，登录业务处理完成!");
    }

    @Override
    public void addNew(UserAddNewParam userAddNewParam) {
        log.debug("开始处理【添加用户】的业务，参数: {}", userAddNewParam);

        // 检查用户名是否被占用
        {
            String username = userAddNewParam.getUsername();
            int count = userRepository.countByUsername(username);
            if (count > 0) {
                String message = "添加用户失败，用户名已经被占用!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        // 检查手机号码是否被占用
        {
            String phone = userAddNewParam.getPhone();
            int count = userRepository.countByPhone(phone);
            if (count > 0) {
                String message = "添加用户失败，手机号码已经被占用!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        // 检查电子邮箱是否被占用
        {
            String email = userAddNewParam.getEmail();
            int count = userRepository.countByEmail(email);
            if (count > 0) {
                String message = "添加用户失败，电子邮箱已经被占用!";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        User user = new User();
        // 复制属性
        BeanUtils.copyProperties(userAddNewParam, user);
        user.setLoginCount(0);
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        int rows = userRepository.insert(user);
        if (rows != 1) {
            String message = "添加用户失败，服务器忙，请稍后再尝试!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        Long[] roleIds = userAddNewParam.getRoleIds();
        List<UserRole> userRoleList = new ArrayList<>();
        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        rows = userRoleRepository.insertBatch(userRoleList);
        if (rows < 1) {
            String message = "添加用户失败，服务器忙，请稍后再尝试!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【根据ID删除用户】的业务，参数: {}", id);
        Object queryResult = userRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "删除用户失败，尝试访问的数据不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        log.debug("即将执行删除数据，参数: {}", id);
        int rows = userRepository.deleteById(id);
        if (rows != 1) {
            String message = "删除用户失败，服务器忙，请稍后再尝试!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        log.debug("即将执行删除关联数据，参数: {}", id);
        rows = userRoleRepository.deleteByAdminId(id);
        if (rows < 1) {
            String message = "删除用户失败，服务器忙，请稍后再尝试!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }
    }

    @Override
    public void setEnable(Long id) {
        updateEnableById(id, 1);
    }

    @Override
    public void setDisable(Long id) {
        updateEnableById(id, 0);
    }

    @Override
    public UserStandardVO getStandardById(Long id) {
        log.debug("开始处理【根据ID查询用户】业务，参数: {}", id);
        UserStandardVO currentUser = userRepository.getStandardById(id);
        if (currentUser == null) {
            String message = "获取用户详情失败，尝试访问的用户数据不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return currentUser;
    }

    @Override
    public PageData<UserListItemVO> list(Integer pageNum) {
        log.debug("开始处理【查询用户列表】的业务，页码: {}", pageNum);
        return userRepository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<UserListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始处理【查询用户列表】的业务，页码: {}，每页记录数: {}", pageNum, pageSize);
        return userRepository.list(pageNum, pageSize);
    }

    private void updateEnableById(Long id, Integer enable) {
        log.debug("开始处理【{}用户】的业务，ID: {}，目标状态: {}", ENABLE_TEXT[enable], id, enable);
        UserStandardVO queryResult = userRepository.getStandardById(id);
        if (queryResult == null) {
            String message = ENABLE_TEXT[enable] + "用户失败，尝试访问的数据不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (queryResult.getEnable().equals(enable)) {
            String message = ENABLE_TEXT[enable] + "用户失败，当前用户已经处于"
                    + ENABLE_TEXT[enable] + "状态!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        User user = new User();
        user.setId(id);
        user.setEnable(enable);
        log.debug("即将修改数据，参数: {}", user);
        int rows = userRepository.updateById(user);
        if (rows != 1) {
            String message = ENABLE_TEXT[enable] + "用户失败，服务器忙，请稍后再次尝试!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

        Integer enableByUserId = cacheRepository.getEnableByUserId(id);
        if (enableByUserId != null) {
            cacheRepository.saveEnableByUserId(id, enable);
        }
    }

}