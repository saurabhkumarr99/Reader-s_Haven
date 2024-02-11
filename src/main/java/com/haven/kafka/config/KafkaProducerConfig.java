package com.haven.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name(AppConstant.ORDER_TOPIC_NAME).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic emailNotificationTopic() {
        return TopicBuilder.name(AppConstant.EMAIL_NOTIFICATION_TOPIC_NAME).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic inventoryManagementTopic() {
        return TopicBuilder.name(AppConstant.INVENTORY_MANAGEMENT_TOPIC_NAME).partitions(1).replicas(1).build();
    }
}
