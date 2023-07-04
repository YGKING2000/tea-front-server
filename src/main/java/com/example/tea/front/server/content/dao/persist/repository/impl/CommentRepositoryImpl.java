package com.example.tea.front.server.content.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.tea.front.server.content.dao.persist.mapper.CommentMapper;
import com.example.tea.front.server.content.dao.persist.repository.ICommentRepository;
import com.example.tea.front.server.content.pojo.entity.Comment;
import com.example.tea.front.server.content.pojo.vo.CommentListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/02 17:01
 */
@Slf4j
@Repository
public class CommentRepositoryImpl implements ICommentRepository {
    @Resource
    private CommentMapper mapper;
    
    public CommentRepositoryImpl() {
        log.debug("创建存储库对象: CommentRepositoryImpl");
    }

    @Override
    public int insert(Comment comment) {
        log.debug("开始执行【发表评论】操作，参数为: {}", comment);
        return mapper.insert(comment);
    }

    @Override
    public Integer selectCount(Long articleId) {
        log.debug("开始执行【根据文章ID查询评论数量】操作，参数为: {}", articleId);
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId);
        return mapper.selectCount(wrapper);
    }

    @Override
    public List<CommentListItemVO> listByArticleId(Long articleId) {
        log.debug("开始执行【根据文章ID查询评论列表】操作，参数为: {}", articleId);
        return mapper.listByArticleId(articleId);
    }
}
