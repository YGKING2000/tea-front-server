package com.example.tea.front.server.content.service;

import com.example.tea.front.server.content.pojo.vo.CategoryListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/30 18:57
 */
@Transactional
public interface ICategoryService {
    /**
     * 重建缓存中的分类数据列表
     */
    void rebuildCacheList();

    /**
     * 获取分类数据列表
     *
     * @return 分类数据列表
     */
    List<CategoryListItemVO> list();
}
