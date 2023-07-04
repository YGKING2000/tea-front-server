package com.example.tea.front.server.content.service;

import com.example.tea.front.server.common.security.CurrentPrincipal;
import com.example.tea.front.server.content.pojo.param.CommentAddNewParam;
import com.example.tea.front.server.content.pojo.vo.CommentListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/02 16:46
 */
@Transactional
public interface ICommentService {
    /**
     * 发表评论
     * @param principal 当事人
     * @param remoteAddr IP地址
     * @param commentAddNewParam 发表的评论数据
     */
    void addNew(CurrentPrincipal principal, String remoteAddr, CommentAddNewParam commentAddNewParam);

    /**
     * 根据文章ID查询评论
     * @param articleId 文章ID
     * @return 评论列表
     */
    List<CommentListItemVO> listByArticleId(Long articleId);
}
