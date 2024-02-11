package com.haven.communication;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public void sendNotification(String Email) {
		
		System.out.println("Notification send to "+Email);
	}
}
