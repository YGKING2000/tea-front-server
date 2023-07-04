package com.example.tea.front.server.content.dao.cache.impl;

import com.example.tea.front.server.common.consts.CacheConstants;
import com.example.tea.front.server.common.pojo.vo.PageData;
import com.example.tea.front.server.content.dao.cache.IArticleCacheRepository;
import com.example.tea.front.server.content.pojo.vo.ArticleListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/01 16:38
 */
@Slf4j
@Repository
public class IArticleCacheRepositoryImpl implements IArticleCacheRepository, CacheConstants {
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void save(List<ArticleListItemVO> articleListItemVOList) {
        log.debug("开始执行【向缓存中写入文章数据列表】操作，参数为: {}", articleListItemVOList);
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        for (Serializable item : articleListItemVOList) {
            opsForList.rightPush(KEY_ARTICLE_LIST, item);
        }
    }

    @Override
    public void deleteList() {
        log.debug("开始执行【删除缓存中的文章数据列表】操作，无参数");
        redisTemplate.delete(KEY_ARTICLE_LIST);
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始执行【从缓存中读取文章数据列表】操作，无参数");
        // try {
        //     Thread.sleep(3000);
        // } catch (InterruptedException e) {
        //     System.out.println(e.getMessage());
        // }
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();

        long start = (long) (pageNum - 1) * pageSize;
        long end = (long) pageNum * pageSize - 1;
        List<Serializable> range = opsForList.range(KEY_ARTICLE_LIST, start, end);
        assert range != null;

        Long total = opsForList.size(KEY_ARTICLE_LIST);
        assert total != null;
        long maxPage = (long) Math.ceil(total * 1.0 / pageSize);

        List<ArticleListItemVO> list = new ArrayList<>();
        for (Serializable item : range) {
            list.add((ArticleListItemVO) item);
        }

        PageData<ArticleListItemVO> pageData = new PageData<>();
        pageData.setCurrentPage(pageNum)
                .setPageSize(pageSize)
                .setList(list)
                .setMaxPage((int) maxPage)
                .setTotal(total);
        return pageData;
    }
}
