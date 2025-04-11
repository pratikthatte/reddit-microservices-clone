package com.reddit.notification_service.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reddit.notification_service.config.RabbitMQConfig;
import com.reddit.notification_service.dto.NotificationDto;

@Service
public class NotificationListener {
	private final ObjectMapper objectMapper;
	public NotificationListener(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}
	@RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
	public void handleNotification(String message) {
		try {
		NotificationDto dto = objectMapper.readValue(message, NotificationDto.class);
        System.out.println("Received notification: " + dto.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
