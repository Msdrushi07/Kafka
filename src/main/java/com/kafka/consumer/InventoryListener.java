package com.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class InventoryListener {

    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consumeInventory(String message) {
        System.out.println("Inventory Service got: " + message);
    }
    
    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consume(OrderEvent event,
                        @Header(KafkaHeaders.RECEIVED_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        ConsumerRecord<String, OrderEvent> record) {
        record.topic();        // Topic name
record.partition();    // Partition number
record.offset();       // Offset of the message
record.key();          // Key of the message
record.value();        // Value of the message (actual message)
record.timestamp();    // Message timestamp
record.headers();      // Message headers
record.serializedKeySize();
record.serializedValueSize();
        log.info("Key={}, Partition={}, Event={}", key, partition, event);
    }
    
// return type is void Log the message Save to DB, Call another service,Trigger an event But you don't return anything
}


