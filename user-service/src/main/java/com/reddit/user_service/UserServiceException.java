package com.reddit.user_service;

@SuppressWarnings("serial")
public class UserServiceException extends RuntimeException{
	public UserServiceException(String message) {
		super(message);
	}
	public UserServiceException(String message, Throwable err) {
		super(message,err);
	}
}
