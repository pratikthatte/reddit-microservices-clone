package com.reddit.post_service.dto;

import java.time.Instant;

public class PostDto {
	private Long postId;
	private Instant postCreationTime;
	private String postName;
	private String postDescription;
	private Long subredditId;
	private Long userId;
	private Long voteCount;
	/**
	 * @param postId the postId to set
	 */
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	/**
	 * @param postCreationTime the postCreationTime to set
	 */
	public void setPostCreationTime(Instant postCreationTime) {
		this.postCreationTime = postCreationTime;
	}
	/**
	 * @param postName the postName to set
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	/**
	 * @param postDescription the postDescription to set
	 */
	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
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
	/**
	 * @return the postId
	 */
	public Long getPostId() {
		return postId;
	}
	/**
	 * @return the postCreationTime
	 */
	public Instant getPostCreationTime() {
		return postCreationTime;
	}
	/**
	 * @return the postName
	 */
	public String getPostName() {
		return postName;
	}
	/**
	 * @return the postDescription
	 */
	public String getPostDescription() {
		return postDescription;
	}
	/**
	 * @return the subredditId
	 */
	public Long getSubredditId() {
		return subredditId;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
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
}
