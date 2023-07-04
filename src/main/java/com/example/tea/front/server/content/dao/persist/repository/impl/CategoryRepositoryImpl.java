package com.example.tea.front.server.content.dao.persist.repository.impl;

import com.example.tea.front.server.content.dao.persist.mapper.CategoryMapper;
import com.example.tea.front.server.content.dao.persist.repository.ICategoryRepository;
import com.example.tea.front.server.content.pojo.vo.CategoryListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/30 19:18
 */
@Slf4j
@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {
    @Resource
    private CategoryMapper mapper;
    
    public CategoryRepositoryImpl() {
        log.info("创建存储库对象: CategoryRepositoryImpl");
    }

    @Override
    public List<CategoryListItemVO> list() {
        log.debug("开始执行【查询分类数据列表】操作，无参数");
        return mapper.getFirstCategoryList();
    }
}
