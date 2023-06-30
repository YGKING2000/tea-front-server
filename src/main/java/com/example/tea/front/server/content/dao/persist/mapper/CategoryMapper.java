package com.example.tea.front.server.content.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tea.front.server.content.pojo.entity.Category;
import com.example.tea.front.server.content.pojo.vo.FirstCategoryListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/30 18:59
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 获取一级分类列表
     *
     * @return 一级分类列表
     */
    List<FirstCategoryListItemVO> getFirstCategoryList();
}
