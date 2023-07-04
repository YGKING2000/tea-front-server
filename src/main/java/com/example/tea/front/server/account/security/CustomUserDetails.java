package com.example.tea.front.server.account.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className CustomUserDetails
 * @date 2023/05/31 10:25
 */
// 因为父类中没有无参构造方法，此时添加@Data会让当前类添加构造方法，此方法可能会和父类的冲突所以报错
@Getter
@ToString(callSuper = true)// 调用父类的toString方法
@EqualsAndHashCode(callSuper = true)// 调用父类的equals方法和hashCode方法
public class CustomUserDetails extends User {
    private final Long id;
    private final String avatar;

    public CustomUserDetails(Long id,
                             String username,
                             String password,
                             String avatar,
                             boolean enable,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enable,
                true, true, true, authorities);
        this.id = id;
        this.avatar = avatar;
    }
}
