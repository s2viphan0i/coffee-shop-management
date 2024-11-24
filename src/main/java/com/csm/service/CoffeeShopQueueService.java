package com.csm.service;

import com.csm.model.ShopOrder;
import com.csm.model.ShopQueue;
import com.csm.repository.entity.CoffeeShopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeShopQueueService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private CoffeeShopService coffeeShopService;

    public void requestQueue(String queueId, ShopOrder shopOrder) {
        shopOrder.setUsername(customUserDetailsService.getCurrentUsername());
        kafkaProducerService.send(shopOrder.getRegion().getValue(), queueId, shopOrder);
    }

    public void processQueue(String queueId, ShopOrder shopOrder) {
        ShopQueue queue = redisService.getQueueFromRedis(queueId);

        if (queue == null) {
            queue = new ShopQueue();
            queue.setQueueId(queueId);
        }
        if (queue.getOrders() == null) {
            queue.setOrders(new ArrayList<>());
        }
        queue.getOrders().add(shopOrder);
        redisService.saveQueueToRedis(queueId, queue);
    }

    public List<ShopQueue> getShopQueue(Long shopId) {
        Integer queues = coffeeShopService.getCoffeeShopById(shopId).getQueue();
        List<ShopQueue> result = new ArrayList<>();
        for (int i = 0; i < queues; i++) {
            ShopQueue queue = redisService.getQueueFromRedis(shopId.toString() + "-" + (i + 1));
            if (queue == null) {
                queue = new ShopQueue();
                queue.setQueueId(shopId + "-" + (i + 1));
            }
            result.add(queue);
        }

        return result;
    }
}