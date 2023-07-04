package com.example.tea.front.server.account.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户登录信息的VO类
 *
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @date 2023/06/13 17:52
 */
@Data
@Accessors(chain = true)
public class UserLoginResultVO implements Serializable {
    /**
     * 数据id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 头像URL
     */
    private String avatar;
    /**
     * JWT
     */
    private String token;
}
