package com.example.tea.front.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/30 11:23
 */
@SpringBootTest
public class RedisTests {
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;
    
    @Test
    void setValue() {
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("username", "胡歌");
    }
    
    @Test
    void getValue() {
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        Serializable username = opsForValue.get("username");
        System.out.println(username);
    }
}
