package com.example.tea.admin.server.common.pojo.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/03 10:13
 */
@Data
public class UserLoginInfoPO implements Serializable {
    /**
     * 用户登录时的ID地址
     */
    private String ip;
    /**
     * 用户登录时的浏览器信息
     */
    private String userAgent;
    /**
     * 用户权限列表字符串
     */
    private String authoritiesJsonString;
}
