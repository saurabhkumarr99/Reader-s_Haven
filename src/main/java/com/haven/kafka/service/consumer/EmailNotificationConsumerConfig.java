package com.haven.kafka.service.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.haven.communication.EmailService;
import com.haven.kafka.config.AppConstant;

@Configuration
public class EmailNotificationConsumerConfig {

	@Autowired
	EmailService emailService;
	
	@KafkaListener(topics = AppConstant.EMAIL_NOTIFICATION_TOPIC_NAME, groupId = "group-1")
	public void consume(ConsumerRecord<String, String> email) {
		System.out.println("Send email notification to customer : "+email.value());
		emailService.sendNotification(email.value());
	}
}
