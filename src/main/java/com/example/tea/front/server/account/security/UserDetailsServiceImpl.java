package com.example.tea.front.server.account.security;

import com.example.tea.front.server.account.dao.persist.repository.IUserRepository;
import com.example.tea.front.server.account.pojo.vo.UserLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/20 18:45
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private IUserRepository userRepository;

    public UserDetailsServiceImpl() {
        log.debug("创建Spring Security的UserDetailsService接口对象: UserDetailsServiceImpl");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Spring Security框架自动调用了UserDetailsServiceImpl对象，根据用户名查询用户详情");
        UserLoginInfoVO loginInfoVO = userRepository.getLoginInfoByUsername(username);
        log.debug("根据【{}】从数据库中查询用户详情，查询结果为: {}", username, loginInfoVO);
        if (loginInfoVO == null) {
            return null;
        }

        List<String> permissions = loginInfoVO.getPermissions();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }

        CustomUserDetails userDetails = new CustomUserDetails(
                loginInfoVO.getId(), loginInfoVO.getUsername(), loginInfoVO.getPassword(), loginInfoVO.getAvatar(),
                loginInfoVO.getEnable() == 1, authorities
        );

        // UserDetails userDetails = User.builder()
        //         .username(loginInfoVO.getUsername())
        //         .password(loginInfoVO.getPassword()) // 此密文的原文是: 123456
        //         .disabled(loginInfoVO.getEnable() == 0) // 账号是否被禁用
        //         .accountLocked(false) // 账号是否被锁定，当前项目中无此概念，则所有账号的此属性都是false
        //         .accountExpired(false) // 账号是否过期，当前项目中无此概念，则所有账号的此属性都是false
        //         .credentialsExpired(false) // 凭证是否过期，当前项目中无此概念，则所有账号的此属性都是false
        //         .authorities(authorities)
        //         .build();
        log.debug("即将向Spring Security框架返回UserDetails类型的结果: {}", userDetails);
        log.debug("接下来，将由Spring Security框架自动验证用户状态、密码等，以判断是否可以成功登录!");
        return userDetails;
    }

}
