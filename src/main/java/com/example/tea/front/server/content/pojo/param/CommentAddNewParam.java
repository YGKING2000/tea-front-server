package com.example.tea.front.server.content.pojo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 评论添加参数类
 *
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/14 15:36
 */
@Data
public class CommentAddNewParam implements Serializable {
    /**
     * 文章ID
     */
    @NotNull(message = "请提交文章ID")
    @Range(min = 1, message = "请提交合法的文章ID值!")
    @ApiModelProperty(value = "文章ID", required = true, example = "9")
    private Long articleId;
    /**
     * 评论内容
     */
    @NotNull(message = "请提交评论内容!")
    @Pattern(regexp = ".{3,200}$", message = "评论内容长度为3~200个字符")
    @ApiModelProperty(value = "评论内容", required = true, example = "红红火火恍恍惚惚")
    private String content;
}
