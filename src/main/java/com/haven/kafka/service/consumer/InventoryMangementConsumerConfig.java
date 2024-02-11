package com.haven.kafka.service.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.haven.kafka.config.AppConstant;
import com.haven.postgress.model.Inventory;
import com.haven.postgress.model.Orders;
import com.haven.postgress.service.InventoryService;

@Configuration
public class InventoryMangementConsumerConfig {

	@Autowired
	InventoryService inventoryService;

	@KafkaListener(topics = AppConstant.INVENTORY_MANAGEMENT_TOPIC_NAME, groupId = "group-1")
	public void consume(ConsumerRecord<String, Orders> order) {
		
		System.out.println("Updated inventory ");
		
		Orders orders = order.value();
		
		int bookId=Integer.valueOf(orders.getBook_id());
		
		Inventory inventory = inventoryService.getInventoryItemById(bookId).get();
		
	    String updated_qty= String.valueOf(Integer.valueOf(inventory.getQuantity())-1);
	    
	    inventory.setQuantity(updated_qty);
		
		inventoryService.updateInventoryItem(inventory.getId(), inventory);
	}
}
