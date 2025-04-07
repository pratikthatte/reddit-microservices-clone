package com.reddit.comment_service.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	private String commentText;
	private Long postId;
	private Long userId;
	private Instant creationTime;
	private Long voteCount;
	@OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies;
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;
	/**
	 * @return the commentId
	 */
	public Long getCommentId() {
		return commentId;
	}
	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}
	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	/**
	 * @return the postId
	 */
	public Long getPostId() {
		return postId;
	}
	/**
	 * @param postId the postId to set
	 */
	public void setPostId(Long postId) {
		this.postId = postId;
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
	 * @return the creationTime
	 */
	public Instant getCreationTime() {
		return creationTime;
	}
	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(Instant creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * @return the replies
	 */
	public List<Comment> getReplies() {
		return replies;
	}
	/**
	 * @param replies the replies to set
	 */
	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}
	/**
	 * @return the parentComment
	 */
	public Comment getParentComment() {
		return parentComment;
	}
	/**
	 * @param parentComment the parentComment to set
	 */
	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}
	public void addReply(Comment reply) {
	    if (this.replies == null) {
	        this.replies = new ArrayList<>();
	    }
	    this.replies.add(reply);
	    reply.setParentComment(this);
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
