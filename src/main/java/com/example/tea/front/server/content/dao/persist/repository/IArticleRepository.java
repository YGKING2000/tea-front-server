package com.example.tea.front.server.content.dao.persist.repository;

import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.content.pojo.vo.ArticleListItemVO;
import com.example.tea.front.server.content.pojo.vo.ArticleStandardVO;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/29 17:30
 */
public interface IArticleRepository {
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
     * 根据类别ID分页查询文章列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 文章分页列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 查询所有文章数据列表
     *
     * @return 文章数据列表
     */
    List<ArticleListItemVO> list();

    /**
     * 根据ID查询文章详情
     *
     * @param id 文章ID
     * @return 文章标准VO类对象
     */
    ArticleStandardVO getStandardById(Long id);
}
