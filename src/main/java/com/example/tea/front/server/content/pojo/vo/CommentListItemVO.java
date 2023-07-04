package com.example.tea.front.server.content.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/02 18:39
 */
@Data
public class CommentListItemVO implements Serializable {
    /**
     * 数据ID
     */
    private Long id;
    /**
     * 作者名字
     */
    private String authorName;
    /**
     * 评论内容
     */
    private String content;
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
     * 数据创建时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtCreate;
}
