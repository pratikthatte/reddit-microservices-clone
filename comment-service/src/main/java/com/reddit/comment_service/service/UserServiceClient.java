package com.reddit.comment_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.reddit.comment_service.config.FeignClientConfig;
import com.reddit.comment_service.dto.UserDto;

@FeignClient (name = "user-service", url = "http://localhost:8081/api/users", configuration = FeignClientConfig.class)
public interface UserServiceClient {
	@GetMapping("/{userId}")
	UserDto getUserDetails(Long userId);
	@GetMapping("/current-user-id")
	Long getCurrentUserId();
	@GetMapping("/publicKey")
	String getPublicKey();
}
