package com.csm.configuration;

import com.csm.model.ShopQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, ShopQueue> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ShopQueue> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer<ShopQueue> serializer = new Jackson2JsonRedisSerializer<>(ShopQueue.class);
        template.setValueSerializer(serializer);

        return template;
    }
}
