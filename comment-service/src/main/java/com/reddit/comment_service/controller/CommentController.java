package com.reddit.comment_service.controller;

import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reddit.comment_service.dto.CommentDto;
import com.reddit.comment_service.dto.VoteDto;
import com.reddit.comment_service.service.CommentService;


@RestController
@RequestMapping("/api/comments")
public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@PostMapping("/add-comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
		return status(HttpStatus.CREATED).body(commentService.save(commentDto));
	}
	@GetMapping("/by-post/{postId}")
	public ResponseEntity<List<CommentDto>> getAllCommentsByPost(@PathVariable Long postId){
		return status(HttpStatus.OK).body(commentService.getAllCommentsByPost(postId));
	}
	@GetMapping("/{id}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id){
		return status(HttpStatus.OK).body(commentService.getCommentById(id));
	}
	@GetMapping("/replies/{id}")
	public ResponseEntity<List<CommentDto>> getAllCommentRepliesById(@PathVariable Long id){
		return status(HttpStatus.OK).body(commentService.getAllCommentRepliesById(id));
	}
	@GetMapping("/by-user/{userId}")
	public ResponseEntity<List<CommentDto>> getAllCommentsByUser(@PathVariable Long userId){
		return status(HttpStatus.OK).body(commentService.getAllCommentsByUser(userId));
	}
	@PostMapping("/update-comment-vote-count")
	public ResponseEntity<String> updateCommentVoteCount(@RequestBody VoteDto voteDto){
		commentService.updateCommentVoteCount(voteDto);
		return status(HttpStatus.OK).body("Comment vote count updated successfully");
	}
}
