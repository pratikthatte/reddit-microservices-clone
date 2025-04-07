package com.reddit.notification_service.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.reddit.notification_service.config.RabbitMQConfig;
import com.reddit.notification_service.dto.NotificationDto;

@Service
public class NotificationListener {
	@RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
	public void handleNotification(NotificationDto message) {
		System.out.println("New notification: " + message.getMessage() + " for user " + message.getUserId());
	}
}
