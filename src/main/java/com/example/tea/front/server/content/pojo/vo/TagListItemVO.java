package com.example.tea.front.server.content.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 列表项VO类: 内容-标签
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Data
public class TagListItemVO implements Serializable {
    /**
     * 数据ID
     */
    private Long id;
    /**
     * 标签名称
     */
    private String name;
}