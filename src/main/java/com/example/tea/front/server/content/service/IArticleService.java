package com.example.tea.front.server.content.service;

import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.content.pojo.vo.ArticleListItemVO;
import com.example.tea.front.server.content.pojo.vo.ArticleStandardVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/29 17:27
 */
@Transactional
public interface IArticleService {
    /**
     * 根据类别ID分页查询文章列表
     *
     * @param categoryId 类别ID
     * @param pageNum    页码
     * @return 文章分页列表
     */
    PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum);

    /**
     * 根据类别ID分页查询文章列表
     *
     * @param categoryId 类别ID
     * @param pageNum    页码
     * @param pageSize   每页记录数
     * @return 文章分页列表
     */
    PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize);

    /**
     * 重建缓存中的文章数据列表
     */
    void rebuildCacheList();

    /**
     * 查询所有文章数据列表
     *
     * @return 文章数据列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum);

    /**
     * 查询所有文章数据列表
     *
     * @return 文章数据列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根据ID查询文章详情
     *
     * @param id 文章ID
     * @return 文章标准VO类对象
     */
    ArticleStandardVO getStandardById(Long id);
}
