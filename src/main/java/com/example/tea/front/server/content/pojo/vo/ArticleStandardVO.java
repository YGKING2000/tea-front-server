package com.example.tea.front.server.content.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章标准VO类
 *
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @date 2023/06/13 19:28
 */
@Data
public class ArticleStandardVO implements Serializable {
    /**
     * 数据ID
     */
    private Long id;
    /**
     * 作者名字
     */
    private String authorName;
    /**
     * 类别名称
     */
    private String categoryName;
    /**
     * 标题
     */
    private String title;
    /**
     * 摘要
     */
    private String brief;
    /**
     * 标签列表，实际存入JSON数据
     */
    private String tags;
    /**
     * 封面图
     */
    private String coverUrl;
    /**
     * 顶数量
     */
    private Integer upCount;
    /**
     * 踩数量
     */
    private Integer downCount;
    /**
     * 浏览量
     */
    private Integer clickCount;
    /**
     * 评论量
     */
    private Integer commentCount;
    /**
     * 详情
     */
    private String detail;
    /**
     * 数据创建时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtCreate;
    /**
     * 数据最后修改时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtModified;
}
