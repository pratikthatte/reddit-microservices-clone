package com.reddit.vote_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class VoteServiceExceptionHandler {
	@ExceptionHandler(VoteServiceException.class)
	public ResponseEntity<String> handleUserNotFoundException(VoteServiceException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
