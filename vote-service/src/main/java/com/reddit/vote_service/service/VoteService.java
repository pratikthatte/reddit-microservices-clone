package com.reddit.vote_service.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reddit.vote_service.dto.VoteDto;
import com.reddit.vote_service.entity.TargetType;
import com.reddit.vote_service.entity.Vote;
import com.reddit.vote_service.entity.VoteType;
import com.reddit.vote_service.repository.VoteRepository;

@Service
public class VoteService {	
	
	private final VoteRepository voteRepository;
	private final UserServiceClient userServiceClient;
	private final PostServiceClient postServiceClient;
	private final CommentServiceClient commentServiceClient;
	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objectMapper;
	
	public VoteService(VoteRepository voteRepository, UserServiceClient userServiceClient, PostServiceClient postServiceClient, CommentServiceClient commentServiceClient, RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
		super();
		this.voteRepository = voteRepository;
		this.userServiceClient = userServiceClient;
		this.postServiceClient = postServiceClient;
		this.commentServiceClient = commentServiceClient;
		this.rabbitTemplate = rabbitTemplate;
		this.objectMapper = objectMapper;
	}
	public void updateVote(Long targetId, VoteType voteType, TargetType targetType) {
		Vote vote = new Vote();
		vote.setUserId(userServiceClient.getCurrentUserId());
		vote.setTargetId(targetId);
		vote.setTargetType(targetType);
		vote.setVoteType(voteType);
		vote.setVoteCreationTime(Instant.now());
		vote = voteRepository.save(vote);
		updateVoteCount(targetId,targetType);
		sendNotification(vote);
		
	}
	private void updateVoteCount(Long targetId, TargetType targetType) {
		VoteDto voteDto = new VoteDto();
		voteDto.setVoteCount(calculateVoteCount(targetId,targetType));
		voteDto.setTargetId(targetId);
		if(targetType == TargetType.POST) {
			postServiceClient.updatePostVoteCount(voteDto);
		}
		else {
			commentServiceClient.updateCommentVoteCount(voteDto);
		}
	}
	private Long calculateVoteCount(Long targetId, TargetType targetType) {
		Long upVoteCount = voteRepository.countByTargetIdAndTargetTypeAndVoteType(targetId, targetType, VoteType.UPVOTE);
		Long downVoteCount = voteRepository.countByTargetIdAndTargetTypeAndVoteType(targetId, targetType,VoteType.DOWNVOTE);
		return upVoteCount-downVoteCount;
	}
	public void sendNotification(Vote vote) {
		try {
			Map<String, Object> payload = new HashMap<>();
	        payload.put("userId", vote.getUserId());
	        payload.put("targetType", vote.getTargetType().name());
	        payload.put("targetId", vote.getTargetId());
	        payload.put("message", "You received a new " + vote.getVoteType().name().toLowerCase()
	                + " on your " + vote.getTargetType().name().toLowerCase());
	        String json = objectMapper.writeValueAsString(payload);
	        rabbitTemplate.convertAndSend("notification-exchange", "notification-queue", json);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		

	
}
