package com.example.tea.front.server.content.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tea.front.server.content.pojo.entity.Comment;
import com.example.tea.front.server.content.pojo.vo.CommentListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/02 17:00
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 根据文章ID查询评论
     * @param articleId 文章ID
     * @return 评论列表
     */
    List<CommentListItemVO> listByArticleId(Long articleId);
}
