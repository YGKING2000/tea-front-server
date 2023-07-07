package com.example.tea.front.server.content.dao.search;

import com.example.tea.admin.server.content.pojo.vo.ArticleSearchVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/06 15:54
 */
@Repository
public interface IArticleSearchRepository extends ElasticsearchRepository<ArticleSearchVO, Long> {
    // 注意事项: 
    // 1.List既不能分页也不能高亮
    // 2.SearchHits只能高亮，不能分页
    // 3.Page只能分页，不能高亮
    // 4.SearchPage既能分页又能高亮
    
    /*
     * 【无法高亮显示关键字】根据关键字搜索文章标题
     *
     * @param title 搜索时的关键字
     * @return 与关键字匹配的文章的列表
     */
    // List<ArticleSearchVO> queryByTitle(String title);

    /*
     * 【高亮显示关键字】根据关键字搜索文章标题
     *
     * @param title 搜索时的关键字
     * @return 与关键字匹配的文章的列表
     */
    // @Highlight(
    //         fields = {@HighlightField(name = "title")},
    //         parameters = @HighlightParameters(preTags = "<font red=red>", postTags = "</font>")
    // )
    // SearchHits<ArticleSearchVO> queryByTitle(String title);

    // 分页查询
    // -- 必须在方法的参数列表的最后位置添加Pageable类型的参数，表示分页参数
    // -- -- 可以通过PageRequest.of(page, size)方法得到Pageable对象，其中，page是以0开始顺序编号的页码值
    // -- 方法的返回结果类型必须是Page类型的
    // Page<ArticleSearchVO> queryByTitle(String title, Pageable pageable);

    @Highlight(
            fields = {@HighlightField(name = "title")},
            parameters = @HighlightParameters(preTags = "<font red=red>", postTags = "</font>")
    )
    SearchPage<ArticleSearchVO> queryByTitle(String title, Pageable pageable);
}
