package com.haven.kafka.service.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.haven.kafka.config.AppConstant;
import com.haven.postgress.model.Orders;
import com.haven.postgress.service.CustomerService;


@Service
public class OrderProducerService {

	@Autowired
	private KafkaTemplate<String, Orders> orderTemplate;
	
	@Autowired
	private KafkaTemplate<String, String> customerTemplate;
	
	@Autowired
	CustomerService customerService;
	
	/**
	 * @author Saurabh Kumar Rai
	 * @apiNote This method publish order on kafka , email of customer and inventory on kafka
	 * @param orders
	 */
	public void handelNewOrder(Orders orders) {
		publishOrder(orders);
		publishEmail(orders);
		publishInventoryUpdate(orders);
	}

	public void publishOrder(Orders orders) {
		orderTemplate.send(AppConstant.ORDER_TOPIC_NAME, orders);
		System.out.println("New Order published : " + orders);
	}
	
	public void publishEmail(Orders orders) {
		
		int customerId = Integer.valueOf(orders.getCustomer_id());
		String customerEmail = customerService.getCustomerById(customerId).get().getEmail();
		
		customerTemplate.send(AppConstant.EMAIL_NOTIFICATION_TOPIC_NAME, customerEmail);
		System.out.println("Publish customer email : " +customerEmail);
	}
	
	public void publishInventoryUpdate(Orders orders) {
		orderTemplate.send(AppConstant.INVENTORY_MANAGEMENT_TOPIC_NAME, orders);
		System.out.println("New Order published to update inventory : " + orders);
	}
	
}
