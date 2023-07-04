package com.example.tea.front.server.content.dao.persist.repository;

import com.example.tea.front.server.content.pojo.entity.Comment;
import com.example.tea.front.server.content.pojo.vo.CommentListItemVO;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/02 16:59
 */
public interface ICommentRepository {
    int insert(Comment comment);

    /**
     * 统计给定文章ID的评论数
     * 
     * @return 统计数量
     */
    Integer selectCount(Long articleId);

    /**
     * 根据文章ID查询评论
     * @param articleId 文章ID
     * @return 评论列表
     */
    List<CommentListItemVO> listByArticleId(Long articleId);
}
