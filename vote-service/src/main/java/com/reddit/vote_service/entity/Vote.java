package com.reddit.vote_service.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vote {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Long userId;
    private Long targetId; 
    private VoteType voteType; 
    private TargetType targetType;
    private Instant voteCreationTime;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the targetId
	 */
	public Long getTargetId() {
		return targetId;
	}
	/**
	 * @param targetId the targetId to set
	 */
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	/**
	 * @return the voteType
	 */
	public VoteType getVoteType() {
		return voteType;
	}
	/**
	 * @param voteType the voteType to set
	 */
	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}
	/**
	 * @return the targetType
	 */
	public TargetType getTargetType() {
		return targetType;
	}
	/**
	 * @param targetType the targetType to set
	 */
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	/**
	 * @return the voteCreationTime
	 */
	public Instant getVoteCreationTime() {
		return voteCreationTime;
	}
	/**
	 * @param voteCreationTime the voteCreationTime to set
	 */
	public void setVoteCreationTime(Instant voteCreationTime) {
		this.voteCreationTime = voteCreationTime;
	}
    
    
}