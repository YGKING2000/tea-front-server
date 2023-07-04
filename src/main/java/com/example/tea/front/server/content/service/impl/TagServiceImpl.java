package com.example.tea.front.server.content.service.impl;

import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.content.dao.persist.repository.ITagRepository;
import com.example.tea.front.server.content.pojo.vo.TagListItemVO;
import com.example.tea.front.server.content.service.ITagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 处理标签数据的业务实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class TagServiceImpl implements ITagService {

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Resource
    private ITagRepository repository;

    public TagServiceImpl() {
        log.info("创建业务对象: TagServiceImpl");
    }

    @Override
    public PageData<TagListItemVO> list(Integer pageNum) {
        log.debug("开始处理【查询标签列表】的业务，页码: {}", pageNum);
        return repository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<TagListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始处理【查询标签列表】的业务，页码: {}，每页记录数: {}", pageNum, pageSize);
        return repository.list(pageNum, pageSize);
    }
}