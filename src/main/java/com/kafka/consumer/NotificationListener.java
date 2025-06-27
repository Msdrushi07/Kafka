package com.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void consumeNotification(String message) {
        System.out.println("Notification Service got: " + message);
    }
}

