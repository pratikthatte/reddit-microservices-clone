package com.reddit.post_service.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	private Instant postCreationTime;
	private String postName;
	private String postDescription;
	private Long subredditId;
	private Long userId;
	private Long voteCount;
	/**
	 * @return the subredditId
	 */
	public long getSubredditId() {
		return subredditId;
	}
	/**
	 * @param subredditId the subredditId to set
	 */
	public void setSubredditId(long subredditId) {
		this.subredditId = subredditId;
	}
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * @return the postId
	 */
	public long getPostId() {
		return postId;
	}
	/**
	 * @return the postCreationTime
	 */
	public Instant getPostCreationTime() {
		return postCreationTime;
	}
	/**
	 * @param postCreationTime the postCreationTime to set
	 */
	public void setPostCreationTime(Instant postCreationTime) {
		this.postCreationTime = postCreationTime;
	}
	/**
	 * @return the postName
	 */
	public String getPostName() {
		return postName;
	}
	/**
	 * @param postName the postName to set
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	/**
	 * @return the postDescription
	 */
	public String getPostDescription() {
		return postDescription;
	}
	/**
	 * @param postDescription the postDescription to set
	 */
	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}
	
	/**
	 * @return the voteCount
	 */
	public Long getVoteCount() {
		return voteCount;
	}
	/**
	 * @param voteCount the voteCount to set
	 */
	public void setVoteCount(Long voteCount) {
		this.voteCount = voteCount;
	}
	/**
	 * @param postId the postId to set
	 */
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	/**
	 * @param subredditId the subredditId to set
	 */
	public void setSubredditId(Long subredditId) {
		this.subredditId = subredditId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}	
}
