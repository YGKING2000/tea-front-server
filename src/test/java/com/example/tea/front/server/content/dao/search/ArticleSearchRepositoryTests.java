package com.example.tea.front.server.content.dao.search;

import com.example.tea.admin.server.content.pojo.vo.ArticleSearchVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/06 16:00
 */
@SpringBootTest
public class ArticleSearchRepositoryTests {
    @Resource
    private IArticleSearchRepository repository;

    // @Test
    // void queryByTitle() {
    //     String title = "普洱茶";
    //     List<ArticleSearchVO> articleSearchVOList = repository.queryByTitle(title);
    //     System.out.println("数据量：" + articleSearchVOList.size());
    //     for (ArticleSearchVO searchVO : articleSearchVOList) {
    //         System.out.println(searchVO.getId() + " >>> " + searchVO.getTitle());
    //     }
    // }

    // @Test
    // void queryByTitle() {
    //     String title = "普洱茶";
    //     SearchHits<ArticleSearchVO> searchHits = repository.queryByTitle(title);
    //     List<SearchHit<ArticleSearchVO>> searchHitList = searchHits.getSearchHits();
    //     for (SearchHit<ArticleSearchVO> searchHit : searchHitList) {
    //         List<String> highlightField = searchHit.getHighlightField("title");
    //         System.out.println(highlightField);
    //         ArticleSearchVO searchVO = searchHit.getContent();
    //         System.out.println(searchVO);
    //     }
    // }

    // @Test
    // void queryByTitle() {
    //     String title = "普洱茶";
    //     int page = 2;
    //     int size = 7;
    //     Pageable pageable = PageRequest.of(page, size);
    //     Page<ArticleSearchVO> articleSearchVOPage = repository.queryByTitle(title, pageable);
    //     int number = articleSearchVOPage.getNumber();
    //     int size1 = articleSearchVOPage.getSize();
    //     int totalPages = articleSearchVOPage.getTotalPages();
    //     List<ArticleSearchVO> articleSearchVOList = articleSearchVOPage.getContent();
    //     System.out.println("number = " + number);
    //     System.out.println("size1 = " + size1);
    //     System.out.println("totalPages = " + totalPages);
    //     System.out.println("articleSearchVOList.size() = " + articleSearchVOList.size());
    //     for (ArticleSearchVO articleSearchVO : articleSearchVOList) {
    //         System.out.println(articleSearchVO);
    //     }
    // }

    @Test
    void queryByTitle() {
        String title = "普洱茶";
        int page = 2;
        int size = 7;
        Pageable pageable = PageRequest.of(page, size);
        SearchPage<ArticleSearchVO> searchPage = repository.queryByTitle(title, pageable);
        int number = searchPage.getNumber();
        int size1 = searchPage.getSize();
        int totalPages = searchPage.getTotalPages();
        List<SearchHit<ArticleSearchVO>> searchHitList = searchPage.getContent();
        System.out.println("number = " + number);
        System.out.println("size1 = " + size1);
        System.out.println("totalPages = " + totalPages);

        for (SearchHit<ArticleSearchVO> searchHit : searchHitList) {
            List<String> highlightField = searchHit.getHighlightField("title");
            System.out.println(highlightField);
            ArticleSearchVO content = searchHit.getContent();
            System.out.println(content);
        }
    }
}
