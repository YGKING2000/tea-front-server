package com.example.tea.front.server.common.security;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/26 11:48
 */
@Data
public class CurrentPrincipal implements Serializable {
    /**
     * 当事人ID
     */
    private Long id;
    /**
     * 当事人用户名
     */
    private String username;
}
