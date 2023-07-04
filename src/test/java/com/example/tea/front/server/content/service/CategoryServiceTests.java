package com.example.tea.front.server.content.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/01 14:41
 */
@SpringBootTest
public class CategoryServiceTests {
    @Resource
    private ICategoryService service;

    @Test
    void rebuildCacheList() {
        service.rebuildCacheList();
    }

    @Test
    void list() {
        List<?> list = service.list();
        for (Object item : list) {
            System.out.println(item);
        }
    }
}
