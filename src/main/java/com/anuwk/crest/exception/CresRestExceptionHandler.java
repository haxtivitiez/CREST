package com.anuwk.crest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CresRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CresErrorResponse> handleException(CustomerNotFoundException cnfe){
		CresErrorResponse error = new CresErrorResponse(HttpStatus.NOT_FOUND.value(),
				cnfe.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<CresErrorResponse> handleException(Exception cnfe){
		CresErrorResponse error = new CresErrorResponse(HttpStatus.BAD_REQUEST.value(),
				cnfe.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

}
