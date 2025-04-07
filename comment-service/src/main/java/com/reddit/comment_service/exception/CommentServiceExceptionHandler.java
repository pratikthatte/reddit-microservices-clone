package com.reddit.comment_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommentServiceExceptionHandler {
	@ExceptionHandler(CommentServiceException.class)
	public ResponseEntity<String> handleCommentException(CommentServiceException ex){
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());		
	}
}
