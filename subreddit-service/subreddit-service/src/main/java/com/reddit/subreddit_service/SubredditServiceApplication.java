package com.reddit.subreddit_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SubredditServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubredditServiceApplication.class, args);
	}

}
