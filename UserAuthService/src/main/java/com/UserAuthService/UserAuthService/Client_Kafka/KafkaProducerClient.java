package com.UserAuthService.UserAuthService.Client_Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerClient {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String topicName,String message) {
        kafkaTemplate.send(topicName,message);
    }
}
