package com.reddit.post_service.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.reddit.post_service.dto.PostDto;
import com.reddit.post_service.dto.VoteDto;
import com.reddit.post_service.entity.Post;
import com.reddit.post_service.exception.PostServiceException;
import com.reddit.post_service.repository.PostRepository;

@Service
public class PostService {
	private final PostRepository postRepository;
	final UserServiceClient userServiceClient;
	public PostService(PostRepository postRepository, UserServiceClient userServiceClient) {
		super();
		this.postRepository = postRepository;
		this.userServiceClient = userServiceClient;
	}
	public PostDto save(PostDto postDto) {
		Post post = postRepository.save(mapToPost(postDto));
		postDto = mapToPostDto(post);
		return postDto;
		
	}
	private Post mapToPost(PostDto postDto) {
		Post post = new Post();
		post.setPostCreationTime(Instant.now());
		post.setPostDescription(postDto.getPostDescription());
		post.setPostName(postDto.getPostName());
		post.setUserId(userServiceClient.getCurrentUserId());
		post.setSubredditId(postDto.getSubredditId());
		post.setVoteCount((long) 0);
		return post;
	}
	private PostDto mapToPostDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setPostCreationTime(post.getPostCreationTime());
		postDto.setPostDescription(post.getPostDescription());
		postDto.setPostId(post.getPostId());
		postDto.setPostName(post.getPostName());
		postDto.setSubredditId(post.getSubredditId());
		postDto.setUserId(post.getUserId());
		postDto.setVoteCount(post.getVoteCount());
		return postDto;
	}
	public List<PostDto> getAllPosts() {
		return postRepository.findAll().stream().map(this::mapToPostDto).collect(Collectors.toList());
	}
	public PostDto getPostById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostServiceException("Invalid post ID: "+id));
		return mapToPostDto(post);
	}
	public List<PostDto> getPostBySubredditId(Long subredditId) {
		return postRepository.findAllBySubredditId(subredditId).stream().map(this::mapToPostDto).collect(Collectors.toList());
	}
	public List<PostDto> getPostByUserId(Long userId) {
		return postRepository.findAllByUserId(userId).stream().map(this::mapToPostDto).collect(Collectors.toList());		
	}
	public void updatePostVoteCount(VoteDto voteDto) {
		Post post = postRepository.findById(voteDto.getTargetId()).orElseThrow(() -> new PostServiceException("Invalid post ID: "+ voteDto.getTargetId()));
		post.setVoteCount(voteDto.getVoteCount());
		postRepository.save(post);
	}
	
	
	
	
}
