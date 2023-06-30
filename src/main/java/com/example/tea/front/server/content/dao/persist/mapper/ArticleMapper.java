package com.example.tea.front.server.content.dao.persist.mapper;

import com.example.tea.front.server.content.pojo.vo.ArticleListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/29 17:24
 */
@Repository
public interface ArticleMapper {
    /**
     * 根据类别ID分页查询文章列表
     *
     * @param categoryId 类别ID
     * @return 文章列表
     */
    List<ArticleListItemVO> listByCategoryId(Long categoryId);
}
