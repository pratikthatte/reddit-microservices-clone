package com.reddit.vote_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reddit.vote_service.entity.TargetType;
import com.reddit.vote_service.entity.VoteType;
import com.reddit.vote_service.service.VoteService;

@RestController
@RequestMapping("/api/votes")
public class VoteController {
	private final VoteService voteService;
	
	public VoteController(VoteService voteService) {
		super();
		this.voteService = voteService;
	}
	
	@PostMapping("/upvote-post/{postId}")
	public ResponseEntity<String> upvotePost(@PathVariable Long postId){
		voteService.updateVote(postId,VoteType.UPVOTE,TargetType.POST);
		return ResponseEntity.ok("Post upvoted");
	}
	@PostMapping("/downvote-post/{postId}")
	public ResponseEntity<String> downvotePost(@PathVariable Long postId){
		voteService.updateVote(postId,VoteType.DOWNVOTE,TargetType.POST);
		return ResponseEntity.ok("Post downvoted");
	}
	@PostMapping("/upvote-comment/{commentId}")
	public ResponseEntity<String> upvoteComment(@PathVariable Long postId){
		voteService.updateVote(postId,VoteType.UPVOTE,TargetType.COMMENT);
		return ResponseEntity.ok("Comment upvoted");
	}
	@PostMapping("/downvote-comment/{commentId}")
	public ResponseEntity<String> downvoteComment(@PathVariable Long postId){
		voteService.updateVote(postId,VoteType.DOWNVOTE,TargetType.COMMENT);
		return ResponseEntity.ok("Comment downvoted");
	}
}
