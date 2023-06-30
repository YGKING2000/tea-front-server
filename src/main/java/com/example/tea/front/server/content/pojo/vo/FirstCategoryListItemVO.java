package com.example.tea.front.server.content.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/30 19:02
 */
@Data
@Accessors(chain = true)
public class FirstCategoryListItemVO implements Serializable {
    /**
     * 数据ID
     */
    private Long id;
    /**
     * 类别名称
     */
    private String name;
}
