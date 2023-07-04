package com.example.tea.front.server.content.service.impl;

import com.example.tea.front.server.common.exception.ServiceException;
import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.common.web.ServiceCode;
import com.example.tea.front.server.content.dao.cache.IArticleCacheRepository;
import com.example.tea.front.server.content.dao.persist.repository.IArticleRepository;
import com.example.tea.front.server.content.pojo.vo.ArticleListItemVO;
import com.example.tea.front.server.content.pojo.vo.ArticleStandardVO;
import com.example.tea.front.server.content.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private IArticleCacheRepository cacheRepository;

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;

    public ArticleServiceImpl() {
        log.info("创建业务对象: ArticleServiceImpl");
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

    @Override
    public void rebuildCacheList() {
        log.debug("开始处理【重建缓存中的文章数据列表】业务，无参数");
        cacheRepository.deleteList();
        List<ArticleListItemVO> list = repository.list();
        cacheRepository.save(list);
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum) {
        log.debug("开始处理【从缓存中读取文章分页数据列表】业务，页码: {}", pageNum);
        return cacheRepository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始处理【从缓存中读取所有文章数据列表】业务，页码: {}，每页记录数: {}", pageNum, pageSize);
        return cacheRepository.list(pageNum, pageSize);
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("开始执行【根据ID查询文章详情】业务，参数为: {}", id);
        ArticleStandardVO standardVO = repository.getStandardById(id);
        if (standardVO == null) {
            String message = "查询详情失败，你查询的数据不存在!";
            log.info(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return standardVO;
    }
}
