package com.reddit.comment_service.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.reddit.comment_service.dto.CommentDto;
import com.reddit.comment_service.dto.VoteDto;
import com.reddit.comment_service.entity.Comment;
import com.reddit.comment_service.exception.CommentServiceException;
import com.reddit.comment_service.repository.CommentRepository;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final UserServiceClient userServiceClient;
	public CommentService(CommentRepository commentRepository, UserServiceClient userServiceClient) {
		super();
		this.commentRepository = commentRepository;
		this.userServiceClient = userServiceClient;
	}
	public CommentDto save(CommentDto commentDto) {
		Comment comment = commentRepository.save(mapToComment(commentDto));
		if(commentDto.getParentCommentId()!=null) saveCommentReply(comment, commentDto.getParentCommentId());
		commentDto = mapToCommentDto(comment);
		return commentDto;
	}
	private void saveCommentReply(Comment comment, Long parentCommentId) {
		Comment parentComment = commentRepository.findById(parentCommentId).get();
		parentComment.addReply(comment);
		commentRepository.save(parentComment);
	}
	private Comment mapToComment(CommentDto commentDto) {
		Comment comment  = new Comment();
		comment.setCommentText(commentDto.getCommentText());
		comment.setUserId(userServiceClient.getCurrentUserId());
		comment.setPostId(commentDto.getPostId());
		if(commentDto.getParentCommentId()!=null)
			comment.setParentComment(commentRepository.findById(commentDto.getParentCommentId()).orElseThrow(() -> new CommentServiceException("Error setting parent comment with id: ")));
		comment.setCreationTime(Instant.now());
		comment.setVoteCount((long) 0);
		return comment;
	}
	private CommentDto mapToCommentDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setCommentId(comment.getCommentId());
		commentDto.setCommentText(comment.getCommentText());
		commentDto.setCreationTime(comment.getCreationTime());
		if(comment.getParentComment()!=null)
			commentDto.setParentCommentId(comment.getParentComment().getCommentId());
		commentDto.setPostId(comment.getPostId());
		commentDto.setUserId(comment.getUserId());
		commentDto.setVoteCount(comment.getVoteCount());
		return commentDto;
	}
	public List<CommentDto> getAllCommentsByPost(Long postId) {
		return commentRepository.findAllByPostId(postId).stream().map(this::mapToCommentDto).collect(Collectors.toList());
	}
	public CommentDto getCommentById(Long id) {
		Comment comment  = commentRepository.findById(id).orElseThrow(() -> new CommentServiceException("Invalid comment id"));
		return mapToCommentDto(comment);
	}
	public List<CommentDto> getAllCommentRepliesById(Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentServiceException("No comment with id: "+id+" found in DB"));
		return comment.getReplies().stream()
			    .map(reply -> {
			        CommentDto dto = mapToCommentDto(reply);
			        return dto;
			    })
			    .collect(Collectors.toList());
	}
	public List<CommentDto> getAllCommentsByUser(Long userId) {
		return commentRepository.findAllByUserId(userId).stream().map(this::mapToCommentDto).collect(Collectors.toList());
	}
	public void updateCommentVoteCount(VoteDto voteDto) {
		Comment comment = commentRepository.findById(voteDto.getTargetId()).orElseThrow(() -> new CommentServiceException("No comment with id: "+voteDto.getTargetId()+" found in DB"));
		comment.setVoteCount(voteDto.getVoteCount());
		commentRepository.save(comment);	
	}
	
}
