package com.reddit.vote_service.dto;

import java.time.Instant;

public class VoteDto {
	private Long voteCount;
    private Long targetId;
	
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
}
