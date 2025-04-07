package com.reddit.comment_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import feign.RequestInterceptor;

@Configuration
public class FeignClientConfig {
	@Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String jwt = getJwtFromContext();
            if (jwt != null) {
                requestTemplate.header("Authorization", "Bearer " + jwt);
            }
        };
    }
	 private String getJwtFromContext() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null && authentication.getCredentials() instanceof String) {
	            return (String) authentication.getCredentials();
	        }
	        return null;
	 }
}
