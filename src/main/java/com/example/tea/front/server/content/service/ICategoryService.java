package com.example.tea.front.server.content.service;

import com.example.tea.front.server.content.pojo.vo.FirstCategoryListItemVO;
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
     * 获取一级分类列表
     *
     * @return 一级分类列表
     */
    List<FirstCategoryListItemVO> getFirstCategoryList();
}
