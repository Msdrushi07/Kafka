package com.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderEventsTopic() {
        return TopicBuilder.name("order-events")
                .partitions(3)
                .replicas(1)
                .build();
    }
    
    
    @Bean
    public NewTopic ordersDltTopic() {
        return new NewTopic("orders.DLT", 3, (short) 1);
    }
    
 // if the replicas is 1 then for each partion 1 broker (manages the messages) is assigned automatically
 // if the replicas is 2 then for each partiotions
//    | Partition | Leader Broker | Follower Broker |
//    | --------- | ------------- | --------------- |
//    | **P0**    | **Broker 1**  | Broker 2        |
//    | **P1**    | **Broker 2**  | Broker 3        |
//    | **P2**    | **Broker 3**  | Broker 1        |
//    If Broker 1 fails, then Partition P0’s leader is down Kafka will promote its follower → Broker 2
// 	Now, Broker 2 is handling:
//     P0 (just promoted)
//     P1 (it was already the leader)

}

