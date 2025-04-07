package com.reddit.subreddit_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SubredditServiceExceptionHandler {
	@ExceptionHandler(SubredditServiceException.class)
	public ResponseEntity<String> handleSubredditException(SubredditServiceException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
