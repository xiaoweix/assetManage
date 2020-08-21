package com.assetManage.tusdt.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String ,Object> redisTemplate;

    // 设置缓存
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 根据 key 获得缓存
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 根据 key 删除缓存
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

}
