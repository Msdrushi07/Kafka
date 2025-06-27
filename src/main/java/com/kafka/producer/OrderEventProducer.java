package com.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class OrderEventProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    

    public void sendOrderEvent(String orderEvent) {
        kafkaTemplate.send("order-events", orderEvent);
    }
    
    public void sendOrderEvent(OrderEvent event) {
        ListenableFuture<SendResult<String, OrderEvent>> future =
            kafkaTemplate.send("order-events", event.getOrderId(), event);

        future.addCallback(
            result -> log.info("Message sent: {}", result),
            ex -> log.error("Failed to send: {}", ex.getMessage())
        );
    }
    
    public String sendOrderEvent(OrderEvent event) {
        kafkaTemplate.send("order-events", event.getOrderId(), event);
        return "Sent successfully";
    }
    
    public ListenableFuture<SendResult<String, OrderEvent>> sendOrderEvent(OrderEvent event) {
        return kafkaTemplate.send("order-events", event.getOrderId(), event);
    }
    
//    Role	Return Type	Valid?	Notes
//    		Producer	void	✅	Fire-and-forget
//    		Producer	String, Object	✅	For status, logging, or testing
//    		Producer	Future<SendResult>	✅	Best for confirming success/failure
//    		Consumer	void	✅	✔️ Only valid return type
//    		Consumer	String, Object	❌	✖️ Ignored by Kafka, may throw errors



}
