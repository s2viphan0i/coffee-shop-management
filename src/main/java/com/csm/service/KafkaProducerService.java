package com.csm.service;

import com.csm.model.ShopOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.kafka.core.KafkaTemplate;

@Service
@Slf4j
public class KafkaProducerService {

    @Value("${kafka.topic.prefix}")
    private String kafkaTopicPrefix;

    @Autowired
    private KafkaTemplate<String, ShopOrder> kafkaTemplate;

    public void send(String topicName, String key, ShopOrder value) {
        var future = kafkaTemplate.send(kafkaTopicPrefix + topicName, key, value);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                future.completeExceptionally(exception);
            } else {
                future.complete(sendResult);
            }
            log.info("Send order to shop: {}, topic: {}", key, topicName);
        });
    }

}