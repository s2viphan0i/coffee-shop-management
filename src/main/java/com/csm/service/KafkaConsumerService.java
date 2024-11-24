package com.csm.service;

import com.csm.model.ShopOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @Value("${kafka.topic.prefix}")
    private String kafkaTopicPrefix;

    @Autowired
    private CoffeeShopQueueService coffeeShopQueueService;

    @KafkaListener(topics = "${kafka.topic.prefix}asia", groupId = "coffee-shop")
    public void listenAsiaRequest(ConsumerRecord<String, ShopOrder> message) {
        log.info("Processing order topic: coffee-shop-asia, message: {}", message.value().toString());
        coffeeShopQueueService.processQueue(message.key(), message.value());
    }

    @KafkaListener(topics = "${kafka.topic.prefix}eu", groupId = "coffee-shop")
    public void listenEURequest(ConsumerRecord<String, ShopOrder> message) {
        log.info("Processing order topic: coffee-shop-eu, message: {}", message.value().toString());
        coffeeShopQueueService.processQueue(message.key(), message.value());
    }

}