package com.haven.kafka.service.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.haven.kafka.config.AppConstant;
import com.haven.postgress.model.Orders;
import com.haven.postgress.service.OrderService;

@Configuration
public class OrderProcessingConsumerConfig {
	
	@Autowired
	OrderService orderService;

	@KafkaListener(topics = AppConstant.ORDER_TOPIC_NAME, groupId = "group-1")
	public void consume(ConsumerRecord<String, Orders> order) {
		System.out.println("Create new Order ");
		orderService.createOrder(order.value());
	}
}
