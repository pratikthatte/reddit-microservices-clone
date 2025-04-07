package com.reddit.subreddit_service.dto;

import java.time.Instant;

public class SubredditDto {
	Long subredditId;
	String subredditName;
	String subredditDescription;
	Instant subredditCreationTime;
	Long createdByUserId;
	/**
	 * @param subredditId the subredditId to set
	 */
	public void setSubredditId(Long subredditId) {
		this.subredditId = subredditId;
	}
	/**
	 * @param subredditName the subredditName to set
	 */
	public void setSubredditName(String subredditName) {
		this.subredditName = subredditName;
	}
	/**
	 * @param subredditDescription the subredditDescription to set
	 */
	public void setSubredditDescription(String subredditDescription) {
		this.subredditDescription = subredditDescription;
	}
	/**
	 * @param subredditCreationTime the subredditCreationTime to set
	 */
	public void setSubredditCreationTime(Instant subredditCreationTime) {
		this.subredditCreationTime = subredditCreationTime;
	}
	/**
	 * @param createdByUserId the createdByUserId to set
	 */
	public void setCreatedByUserId(Long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}
	/**
	 * @return the subredditId
	 */
	public Long getSubredditId() {
		return subredditId;
	}
	/**
	 * @return the subredditName
	 */
	public String getSubredditName() {
		return subredditName;
	}
	/**
	 * @return the subredditDescription
	 */
	public String getSubredditDescription() {
		return subredditDescription;
	}
	/**
	 * @return the subredditCreationTime
	 */
	public Instant getSubredditCreationTime() {
		return subredditCreationTime;
	}
	/**
	 * @return the createdByUserId
	 */
	public Long getCreatedByUserId() {
		return createdByUserId;
	}
	
}
