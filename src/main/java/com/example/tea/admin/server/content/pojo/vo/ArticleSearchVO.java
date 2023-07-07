package com.example.tea.admin.server.content.pojo.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/06 10:48
 */
@Data
@Document(indexName = "article")
public class ArticleSearchVO implements Serializable {
    /**
     * 数据ID
     */
    @Id
    private Long id;
    /**
     * 作者ID
     */
    private Long authorId;
    /**
     * 作者名字
     */
    private String authorName;
    /**
     * 标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String title;
    /**
     * 摘要
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String brief;
    /**
     * 排序序号
     */
    private Integer sort;
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
     * 数据创建时间
     */
    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
    /**
     * 数据最后修改时间
     */
    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
}
