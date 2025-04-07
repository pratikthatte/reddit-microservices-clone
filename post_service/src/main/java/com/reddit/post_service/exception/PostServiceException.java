package com.reddit.post_service.exception;

@SuppressWarnings("serial")
public class PostServiceException extends RuntimeException{
	public PostServiceException(String message){
		super(message);
	}
	public PostServiceException(String message, Throwable err) {
		super(message,err);
	}
}
