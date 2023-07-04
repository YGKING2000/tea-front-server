package com.example.tea.front.server.content.dao.cache;

import com.example.tea.front.server.content.pojo.vo.CategoryListItemVO;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/01 11:58
 */
public interface ICategoryCacheRepository {
    /**
     * 向缓存中写入分类数据列表
     *
     * @param categoryListItemVOList 分类数据列表
     */
    void save(List<CategoryListItemVO> categoryListItemVOList);

    /**
     * 删除缓存中的分类数据列表
     *
     * @return 删除的结果
     */
    void deleteList();

    /**
     * 从缓存中读取分类数据列表
     *
     * @return List<CategoryListItemVO> 分类数据列表
     */
    List<CategoryListItemVO> list();
}
