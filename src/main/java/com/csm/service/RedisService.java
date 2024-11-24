package com.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveQueueToRedis(String key, Person person) {
        redisTemplate.opsForValue().set(key, person);
    }

    public Person getPersonFromRedis(String key) {
        return (Person) redisTemplate.opsForValue().get(key);
    }
}
