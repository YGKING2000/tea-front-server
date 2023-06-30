package com.example.tea.front.server.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @className PageData
 * @date 2023/06/17 11:18
 */
@Data
@Accessors(chain = true)
public class PageData<T> implements Serializable {
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 记录总数
     */
    private Long total;
    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 最大页码
     */
    private Integer maxPage;
    /**
     * 数据列表
     */
    private List<T> list;
}
