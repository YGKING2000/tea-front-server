package com.example.tea.front.server.content.dao.persist.repository;

import com.example.tea.front.server.content.pojo.vo.CategoryListItemVO;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/30 19:17
 */
public interface ICategoryRepository {
    /**
     * 获取分类数据列表
     *
     * @return 分类数据列表
     */
    List<CategoryListItemVO> list();
}
