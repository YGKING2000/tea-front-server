package com.example.tea.front.server.content.dao.persist.repository.impl;

import com.example.tea.front.server.common.util.PageInfoToPageDataConverter;
import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.content.dao.persist.mapper.TagMapper;
import com.example.tea.front.server.content.dao.persist.repository.ITagRepository;
import com.example.tea.front.server.content.pojo.vo.TagListItemVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 处理标签数据的存储库实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class TagRepositoryImpl implements ITagRepository {

    @Resource
    private TagMapper mapper;

    public TagRepositoryImpl() {
        log.info("创建存储库对象: TagRepositoryImpl");
    }

    @Override
    public PageData<TagListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始执行【查询标签列表】，页码: {}，每页记录数: {}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<TagListItemVO> list = mapper.list();
        PageInfo<TagListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

}