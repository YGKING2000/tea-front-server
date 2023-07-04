package com.example.tea.front.server.content.dao.cache;

import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.content.pojo.vo.ArticleListItemVO;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/01 11:58
 */
public interface IArticleCacheRepository {
    /**
     * 向缓存中写入分类数据列表
     */
    void save(List<ArticleListItemVO> articleListItemVOList);

    /**
     * 删除缓存中的分类数据列表
     */
    void deleteList();

    /**
     * 从缓存中读取分类数据列表
     *
     * @return List<CategoryListItemVO> 分类数据列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);
}
