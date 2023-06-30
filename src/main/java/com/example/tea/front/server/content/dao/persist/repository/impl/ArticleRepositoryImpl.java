package com.example.tea.front.server.content.dao.persist.repository.impl;

import com.example.tea.front.server.common.util.PageInfoToPageDataConverter;
import com.example.tea.front.server.common.vo.PageData;
import com.example.tea.front.server.content.dao.persist.mapper.ArticleMapper;
import com.example.tea.front.server.content.dao.persist.repository.IArticleRepository;
import com.example.tea.front.server.content.pojo.vo.ArticleListItemVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/29 17:31
 */
@Slf4j
@Repository
public class ArticleRepositoryImpl implements IArticleRepository {
    @Resource
    private ArticleMapper mapper;

    public ArticleRepositoryImpl() {
        log.debug("创建存储库对象: ArticleRepositoryImpl");
    }

    @Override
    public PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("开始执行【根据类别ID({})查询文章分页列表】操作，页码: {}，每页记录数: {}", categoryId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = mapper.listByCategoryId(categoryId);
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
