package com.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consumeOrder(String orderEvent) {
        System.out.println("Inventory Service received: " + orderEvent);
        // Parse and update inventory
    }
}

//| Condition                              | What Happens                                 |
//| -------------------------------------- | -------------------------------------------- |
//| 3 partitions, 1 consumer               | One consumer reads from all partitions       |
//| 3 partitions, 3 consumers (same group) | Kafka distributes one partition per consumer |
//| Messages published without key         | Kafka distributes them round-robin           |
//| Messages published with same key       | All go to same partition                     |

//if we specify key
//  kafkaTemplate.send("order-events", "user1", "Order-1");
//  kafkaTemplate.send("order-events", "user2", "Order-2");
//  kafkaTemplate.send("order-events", "user3", "Order-3");
//  kafkaTemplate.send("order-events", "user1", "Order-4");

//| Benefit                 | How It Helps                                           |
//| ----------------------- | ------------------------------------------------------ |
//| Ordering per key        | All events for same user/product go to same partition  |
//| Consistent processing   | Allows a single consumer to handle all events per key  |
//| Partition-aware scaling | You can scale based on keys for efficient partitioning |


