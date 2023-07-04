package com.example.tea.front.server.content.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tea.front.server.content.pojo.entity.Article;
import com.example.tea.front.server.content.pojo.entity.Category;
import com.example.tea.front.server.content.pojo.vo.ArticleListItemVO;
import com.example.tea.front.server.content.pojo.vo.ArticleStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/29 17:24
 */
@Repository
public interface ArticleMapper extends BaseMapper<Category> {
    /**
     * 根据类别ID分页查询文章列表
     *
     * @param categoryId 类别ID
     * @return 文章列表
     */
    List<ArticleListItemVO> listByCategoryId(Long categoryId);

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
