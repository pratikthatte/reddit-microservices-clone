package com.reddit.vote_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.reddit.vote_service.config.FeignClientConfig;
import com.reddit.vote_service.dto.VoteDto;

@FeignClient(name = "comment-service", url = "http://localhost:8084/api/comments", configuration = FeignClientConfig.class)
public interface CommentServiceClient {
	@PostMapping("/update-comment-vote-count")
	public void updateCommentVoteCount(@RequestBody VoteDto voteDto);
}
