package com.reddit.post_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.reddit.post_service.config.FeignClientConfig;
import com.reddit.post_service.dto.UserDto;

@FeignClient(name="user-service", configuration = FeignClientConfig.class)
public interface UserServiceClient {
	@GetMapping("/{userId}")
	UserDto getUserDetails(Long userId);
	@GetMapping("/current-user-id")
	Long getCurrentUserId();
	@GetMapping("/publicKey")
	String getPublicKey();
}
