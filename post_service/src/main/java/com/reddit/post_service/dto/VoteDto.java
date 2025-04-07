package com.reddit.post_service.dto;

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
	 * @return the commentId
	 */
	public Long getTargetId() {
		return targetId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
}
