package com.example.tea.front.server.account.dao.cache.impl;

import com.example.tea.front.server.account.dao.cache.IUserCacheRepository;
import com.example.tea.admin.server.common.pojo.po.UserLoginInfoPO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/03 10:16
 */
@Repository
public class IUserCacheRepositoryImpl implements IUserCacheRepository {

    @Value("${tea-store.jwt.duration-in-minute}")
    private Long durationInMinute;

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void saveLoginInfo(String jwt, UserLoginInfoPO userLoginInfoPO) {
        String key = USER_JWT_PREFIX + jwt;
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, userLoginInfoPO, durationInMinute, TimeUnit.MINUTES);
        // opsForValue.set(key, userLoginInfoPO, 20, TimeUnit.SECONDS);
    }

    @Override
    public void saveEnableByUserId(Long userId, Integer enable) {
        String key = USER_ENABLE_PREFIX + userId;
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, enable);
    }

    public UserLoginInfoPO getLoginInfo(String jwt) {
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        String key = USER_JWT_PREFIX + jwt;
        Serializable serializable = opsForValue.get(key);
        return (UserLoginInfoPO) serializable;
    }

    @Override
    public Integer getEnableByUserId(Long userId) {
        String key = USER_ENABLE_PREFIX + userId;
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        return (Integer) opsForValue.get(key);
    }


}
