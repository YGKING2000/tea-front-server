package com.example.tea.front.server.content.service.impl;

import com.example.tea.front.server.common.vo.PageData;
import com.example.tea.front.server.content.dao.persist.repository.IArticleRepository;
import com.example.tea.front.server.content.pojo.vo.ArticleListItemVO;
import com.example.tea.front.server.content.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/29 17:28
 */
@Slf4j
@Service
public class ArticleServiceImpl implements IArticleService {
    @Resource
    private IArticleRepository repository;

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;

    public ArticleServiceImpl() {
        log.debug("创建业务对象: ArticleServiceImpl");
    }

    @Override
    public PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum) {
        log.debug("开始处理【根据类别ID({})分页查询文章列表，页码: {}】业务", categoryId, pageNum);
        return repository.listByCategoryId(categoryId, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("开始处理【根据类别ID({})分页查询文章列表，页码: {}】业务，每页记录数: {}", categoryId, pageNum, pageSize);
        return repository.listByCategoryId(categoryId, pageNum, pageSize);
    }
}
