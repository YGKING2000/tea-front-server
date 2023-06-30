package com.example.tea.front.server.content.service.impl;

import com.example.tea.front.server.content.dao.persist.repository.ICategoryRepository;
import com.example.tea.front.server.content.pojo.vo.FirstCategoryListItemVO;
import com.example.tea.front.server.content.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/30 18:58
 */
@Slf4j
@Repository
public class CategoryServiceImpl implements ICategoryService {
    @Resource
    private ICategoryRepository repository;
    
    public CategoryServiceImpl() {
        log.debug("创建业务对象: CategoryServiceImpl");
    }

    @Override
    public List<FirstCategoryListItemVO> getFirstCategoryList() {
        log.debug("开始处理【查询一级分类列表】业务，无参数");
        return repository.getFirstCategoryList();
    }
}
