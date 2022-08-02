package com.makeawish.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
	

	@ExceptionHandler(ApplicationException.class)
	public String handleException() {
		System.out.println("In global Exception handler");
		return "error";
	}

}
