package com.reddit.vote_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.reddit.vote_service.config.FeignClientConfig;
import com.reddit.vote_service.dto.VoteDto;

@FeignClient(name = "post-service", url = "http://localhost:8082/api/posts", configuration = FeignClientConfig.class)
public interface PostServiceClient {
	@PostMapping("/update-post-vote-count")
	public void updatePostVoteCount(@RequestBody VoteDto voteDto);
}
