package com.reddit.subreddit_service.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.reddit.subreddit_service.dto.SubredditDto;
import com.reddit.subreddit_service.dto.UserDto;
import com.reddit.subreddit_service.entity.Subreddit;
import com.reddit.subreddit_service.exception.SubredditServiceException;
import com.reddit.subreddit_service.repository.SubredditRepository;

@Service
public class SubredditService {
	private final SubredditRepository subredditRepository;
	private final UserServiceClient userServiceClient;

	public SubredditService(SubredditRepository subredditRepository, UserServiceClient userServiceClient) {
		super();
		this.subredditRepository = subredditRepository;
		this.userServiceClient = userServiceClient;
	}

	public List<SubredditDto> getAllSubreddits() {
		return subredditRepository.findAll().stream().map(this::mapToSubredditDto).collect(Collectors.toList());
	}
	private SubredditDto mapToSubredditDto(Subreddit subreddit) {
		SubredditDto subredditDto = new SubredditDto();
		subredditDto.setSubredditCreationTime(subreddit.getSubredditCreationTime());
		subredditDto.setSubredditId(subreddit.getSubredditId());
		subredditDto.setSubredditDescription(subreddit.getSubredditDescription());
		subredditDto.setSubredditName(subreddit.getSubredditName());
		return subredditDto;
	}

	public SubredditDto getSubredditById(Long subredditId) {
		Subreddit subreddit = subredditRepository.findById(subredditId).orElseThrow(() -> new SubredditServiceException("Invalid subreddit id: " + subredditId));
		return mapToSubredditDto(subreddit);
	}

	public SubredditDto createSubreddit(SubredditDto subredditDto) {
		Subreddit subreddit = subredditRepository.save(mapToSubreddit(subredditDto));
		return mapToSubredditDto(subreddit);
		
	}
	private Subreddit mapToSubreddit(SubredditDto subredditDto) {
		Subreddit subreddit = new Subreddit();
		subreddit.setSubredditName(subredditDto.getSubredditName());
		subreddit.setSubredditDescription(subredditDto.getSubredditDescription());
		subreddit.setSubredditCreationTime(Instant.now());
		subreddit.setCreatedByUserId(userServiceClient.getCurrentUserId());	
		return subreddit;
	}

	public UserDto getCreatorUserDetails(Long subredditId) {
		Subreddit subreddit = subredditRepository.findById(subredditId).orElseThrow(() -> new SubredditServiceException("Invalid subreddit id: " + subredditId));		
		return userServiceClient.getUserDetails(subreddit.getCreatedByUserId());
	}
	
}
