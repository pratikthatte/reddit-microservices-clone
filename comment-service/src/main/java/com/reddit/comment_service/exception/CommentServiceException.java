package com.reddit.comment_service.exception;

public class CommentServiceException extends RuntimeException{
	public CommentServiceException(String message) {
		super(message);
	}
	public CommentServiceException(String message, Throwable err) {
		super(message,err);
	}
}
