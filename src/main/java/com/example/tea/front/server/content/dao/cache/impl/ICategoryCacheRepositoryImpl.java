package com.example.tea.front.server.content.dao.cache.impl;

import com.example.tea.front.server.common.consts.CacheConstants;
import com.example.tea.front.server.content.dao.cache.ICategoryCacheRepository;
import com.example.tea.front.server.content.pojo.vo.CategoryListItemVO;
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
 * @date 2023/07/01 11:59
 */
@Slf4j
@Repository
public class ICategoryCacheRepositoryImpl implements ICategoryCacheRepository, CacheConstants {

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void save(List<CategoryListItemVO> categoryListItemVOList) {
        log.debug("开始执行【向缓存中写入类别列表】操作，参数为: {}", categoryListItemVOList);
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        for (CategoryListItemVO item : categoryListItemVOList) {
            opsForList.rightPush(KEY_CATEGORY_LIST, item);
        }
    }

    @Override
    public void deleteList() {
        log.debug("开始执行【删除缓存中的分类数据列表】操作，无参数");
        redisTemplate.delete(KEY_CATEGORY_LIST);
    }

    @Override
    public List<CategoryListItemVO> list() {
        log.debug("开始执行【从缓存中读取类别列表】操作，无参数");
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        List<Serializable> serializableList = opsForList.range(KEY_CATEGORY_LIST, 0, -1);
        assert serializableList != null;

        List<CategoryListItemVO> list = new ArrayList<>();
        for (Serializable item : serializableList) {
            list.add((CategoryListItemVO) item);
        }
        return list;
    }
}
