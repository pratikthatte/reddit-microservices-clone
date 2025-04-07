package com.reddit.vote_service.exception;

@SuppressWarnings("serial")
public class VoteServiceException extends RuntimeException{
	public VoteServiceException(String message) {
		super(message);
	}
	public VoteServiceException(String message, Throwable err) {
		super(message,err);
	}
}
