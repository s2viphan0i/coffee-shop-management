package com.csm.service;

import com.csm.model.ShopQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, ShopQueue> redisTemplate;

    public void saveQueueToRedis(String queueKey, ShopQueue queues) {
        redisTemplate.opsForValue().set(queueKey, queues);
    }

    public ShopQueue getQueueFromRedis(String queueId) {
        return redisTemplate.opsForValue().get(queueId);
    }
}