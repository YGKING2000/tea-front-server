package com.example.tea.front.server.content.service.impl;

import com.example.tea.front.server.common.exception.ServiceException;
import com.example.tea.front.server.common.security.CurrentPrincipal;
import com.example.tea.front.server.common.web.ServiceCode;
import com.example.tea.front.server.content.dao.persist.repository.IArticleRepository;
import com.example.tea.front.server.content.dao.persist.repository.ICommentRepository;
import com.example.tea.front.server.content.pojo.entity.Comment;
import com.example.tea.front.server.content.pojo.param.CommentAddNewParam;
import com.example.tea.front.server.content.pojo.vo.ArticleStandardVO;
import com.example.tea.front.server.content.pojo.vo.CommentListItemVO;
import com.example.tea.front.server.content.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/02 16:48
 */
@Slf4j
@Service
public class CommentServiceImpl implements ICommentService {
    @Resource
    private ICommentRepository repository;
    
    @Resource
    private IArticleRepository articleRepository;

    public CommentServiceImpl() {
        log.debug("创建业务对象: CommentServiceImpl");
    }

    @Override
    public void addNew(CurrentPrincipal principal, String remoteAddr, CommentAddNewParam commentAddNewParam) {
        log.debug("开始处理【发表评论】业务，参数为");

        Long articleId = commentAddNewParam.getArticleId();
        ArticleStandardVO standardVO = articleRepository.getStandardById(articleId);
        if (standardVO == null) {
            String message = "发表评论失败，你评论的文章不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        Integer floor = repository.selectCount(articleId);
        Long id = principal.getId();
        String username = principal.getUsername();
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentAddNewParam, comment);
        comment.setAuthorId(id)
                .setAuthorName(username)
                .setIp(remoteAddr)
                .setFloor(floor + 1)
                .setUpCount(0)
                .setDownCount(0)
                .setCheckState(0)
                .setCheckRemarks("")
                .setReferenceIds("")
                .setDisplayState(0);

        int rows = repository.insert(comment);
        if (rows != 1) {
            String message = "发表评论失败，请稍后重新尝试!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UNKNOWN, message);
        }
    }

    @Override
    public List<CommentListItemVO> listByArticleId(Long articleId) {
        log.debug("开始处理【根据文章ID查询评论】业务，参数为: {}", articleId);
        return repository.listByArticleId(articleId);
    }
}
