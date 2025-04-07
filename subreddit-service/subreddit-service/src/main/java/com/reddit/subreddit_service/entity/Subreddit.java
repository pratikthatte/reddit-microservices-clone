package com.reddit.subreddit_service.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Subreddit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long subredditId;
	String subredditName;
	String subredditDescription;
	Instant subredditCreationTime;
	Long createdByUserId;
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
	 * @param subredditName the subredditName to set
	 */
	public void setSubredditName(String subredditName) {
		this.subredditName = subredditName;
	}
	/**
	 * @return the subredditDescription
	 */
	public String getSubredditDescription() {
		return subredditDescription;
	}
	/**
	 * @param subredditDescription the subredditDescription to set
	 */
	public void setSubredditDescription(String subredditDescription) {
		this.subredditDescription = subredditDescription;
	}
	/**
	 * @return the subredditCreationTime
	 */
	public Instant getSubredditCreationTime() {
		return subredditCreationTime;
	}
	/**
	 * @param subredditCreationTime the subredditCreationTime to set
	 */
	public void setSubredditCreationTime(Instant subredditCreationTime) {
		this.subredditCreationTime = subredditCreationTime;
	}
	/**
	 * @return the createdByUserId
	 */
	public Long getCreatedByUserId() {
		return createdByUserId;
	}
	/**
	 * @param createdByUserId the createdByUserId to set
	 */
	public void setCreatedByUserId(Long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}
	/**
	 * @param subredditId the subredditId to set
	 */
	public void setSubredditId(Long subredditId) {
		this.subredditId = subredditId;
	}
}
