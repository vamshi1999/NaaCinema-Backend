package com.naacinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.naacinema.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CinemaNotFoundException.class)
	public ResponseEntity<ApiResponse> cinemaNotFoundException(CinemaNotFoundException ex){
		
		String message=ex.getMessage();
		ApiResponse response=new ApiResponse(message,false);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}

}
