package com.converter.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.converter.task.exception.ErrorMessage;

@RestControllerAdvice
public class ExceptionHelper {
	

	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<ErrorMessage> handleException(Exception ex) {
		ErrorMessage m = new ErrorMessage();
		m.setMessage(ex.getMessage());
	    return new ResponseEntity<ErrorMessage>(m,HttpStatus.ACCEPTED);
	}
  
	

}
