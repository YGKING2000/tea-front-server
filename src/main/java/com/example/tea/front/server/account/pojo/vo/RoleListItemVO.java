package com.example.tea.front.server.account.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色的列表项VO类
 *
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @date 2023/06/13 17:52
 */
@Data
public class RoleListItemVO implements Serializable {

    /**
     * 数据id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 自定义排序序号
     */
    private Integer sort;

}
