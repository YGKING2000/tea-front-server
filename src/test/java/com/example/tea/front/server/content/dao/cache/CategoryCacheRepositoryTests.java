package com.example.tea.front.server.content.dao.cache;

import com.example.tea.front.server.content.dao.persist.repository.ICategoryRepository;
import com.example.tea.front.server.content.pojo.vo.CategoryListItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/01 12:29
 */
@SpringBootTest
public class CategoryCacheRepositoryTests {
    @Resource
    private ICategoryRepository repository;

    @Resource
    private ICategoryCacheRepository cacheRepository;

    @Test
    void save() {
        List<CategoryListItemVO> list = repository.list();
        cacheRepository.save(list);
    }
    
    @Test
    void list() {
        List<CategoryListItemVO> list = cacheRepository.list();
        for (Object item : list) {
            System.out.println(item);
        }
    }
    
    @Test
    void delete() {
        cacheRepository.deleteList();
    }
}
