package com.example.tea.front.server.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 评论实体类
 *
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @date 2023/06/13 19:48
 */
@Data
@Accessors(chain = true)
@TableName("content_comment")
public class Comment implements Serializable {
    /**
     * 数据ID
     */
    @TableId(type = IdType.AUTO)
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
     * 文章ID
     */
    private Long articleId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * IP
     */
    private String ip;
    /**
     * 楼层
     */
    private Integer floor;
    /**
     * 顶数量
     */
    private Integer upCount;
    /**
     * 踩数量
     */
    private Integer downCount;
    /**
     * 审核状态，0=未审核，1=审核通过，2=拒绝审核
     */
    private Integer checkState;
    /**
     * 审核原因
     */
    private String checkRemarks;
    /**
     * 引用评论ID
     */
    private String referenceIds;
    /**
     * 显示状态，0=不显示，1=显示
     */
    private Integer displayState;
    /**
     * 数据创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;
    /**
     * 数据最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
