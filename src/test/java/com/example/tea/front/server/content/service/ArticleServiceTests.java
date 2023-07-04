package com.example.tea.front.server.content.service;

import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.content.dao.cache.IArticleCacheRepository;
import com.example.tea.front.server.content.pojo.vo.ArticleListItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/01 18:35
 */
@SpringBootTest
public class ArticleServiceTests {
    @Resource
    private IArticleService service;

    @Resource
    private IArticleCacheRepository cacheRepository;

    @Test
    void list() {
        PageData<ArticleListItemVO> pageData = cacheRepository.list(1, 5);
        for (Object item : pageData.getList()) {
            System.out.println(item);
        }
    }
    
    @Test
    void rebuildCacheList() {
        service.rebuildCacheList();
        System.out.println("成功");
    }
}
