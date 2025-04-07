package com.reddit.subreddit_service.exception;

@SuppressWarnings("serial")
public class SubredditServiceException extends RuntimeException{
	public SubredditServiceException(String message) {
		super(message);
	}
	public SubredditServiceException(String message, Throwable err) {
		super(message,err);
	}
}
