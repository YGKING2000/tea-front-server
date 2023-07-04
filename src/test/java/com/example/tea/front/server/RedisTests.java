package com.example.tea.front.server;

import com.example.tea.front.server.content.pojo.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/06/30 11:23
 */
@SpringBootTest
public class RedisTests {
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;


    // 当读写操作与值无关时，直接调用redisTemplate的方法
    // 当需要读写Redis中string类型的数据时，需要先opsForValue
    // 当需要读写Redis中list类型的数据时，需要先opsForList
    // 当需要读写Redis中set类型的数据时，需要先opsForSet

    @Test
    void setValue() {
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("username", "胡歌");
        opsForValue.set("password", "123456");


        opsForValue.set("username1", "胡歌1");
        opsForValue.set("username2", "胡歌2");
        opsForValue.set("username3", "胡歌3");
        opsForValue.set("username4", "胡歌4");
        opsForValue.set("username5", "胡歌5");
    }

    @Test
    void getValue() {
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        Serializable username = opsForValue.get("username");
        System.out.println(username);
    }

    @Test
    void setObjectValue() {
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        Tag tag = new Tag();
        tag.setName("哇哈哈功能饮料");
        tag.setSort(100);
        opsForValue.set("tag", tag);
    }

    @Test
    void getObjectValue() {
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        Serializable tag = opsForValue.get("tag");
        System.out.println(tag);
    }

    @Test
    void keys() {
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        String pattern = "*";
        Set<String> keys = redisTemplate.keys(pattern);
        assert keys != null;
        for (Serializable key : keys) {
            System.out.println(opsForValue.get(key));
        }
    }

    @Test
    void delete() {
        Boolean delete = redisTemplate.delete("username5");
        System.out.println(delete);
    }

    @Test
    void deleteBatch() {
        ArrayList<String> list = new ArrayList<>();
        list.add("username1");
        list.add("username2");
        list.add("username3");
        list.add("username4");
        list.add("username5");
        list.add("username6");
        Long delete = redisTemplate.delete(list);
        System.out.println(delete);
    }

    // 向Redis中顺序存入list数据，具体内容为8个字符串
    @Test
    void rightPush() {
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        String key = "nameList1";

        opsForList.rightPush(key, "username1");
        opsForList.rightPush(key, "username2");
        opsForList.rightPush(key, "username3");
        opsForList.rightPush(key, "username4");
        opsForList.rightPush(key, "username5");
        opsForList.rightPush(key, "username6");
        opsForList.rightPush(key, "username7");
        opsForList.rightPush(key, "username8");
    }

    // 向Redis中逆序存入list数据，具体内容为8个字符串
    @Test
    void leftPush() {
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        String key = "nameList2";

        opsForList.leftPush(key, "username1");
        opsForList.leftPush(key, "username2");
        opsForList.leftPush(key, "username3");
        opsForList.leftPush(key, "username4");
        opsForList.leftPush(key, "username5");
        opsForList.leftPush(key, "username6");
        opsForList.leftPush(key, "username7");
        opsForList.leftPush(key, "username8");
    }

    // 从Redis中读取list数据
    @Test
    void range() {
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        String key = "nameList1";
        //  0  1  2  3  4  5  6  7
        // -8 -7 -6 -5 -4 -3 -2 -1 
        long start = 0;
        long end = -1;

        List<Serializable> list = opsForList.range(key, start, end);
        assert list != null;
        System.out.println("读取列表完成，列表长度：" + list.size());
        for (Serializable serializable : list) {
            System.out.println(serializable);
        }
    }
}
