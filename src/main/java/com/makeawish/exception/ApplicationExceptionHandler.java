package com.makeawish.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler({ ApplicationException.class, NullPointerException.class })
	public String handleException() {
		System.out.println("In global Exception handler ");
		return "error";
	}

	@ExceptionHandler({ LoginFailureException.class })
	public ResponseEntity handleLoginException(LoginFailureException ex) {
		System.out.println("In global Exception handler ");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
	}

}
