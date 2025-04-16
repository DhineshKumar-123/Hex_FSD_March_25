package com.mockevaluation.book_api.config;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mockevaluation.book_api.exception.InvalidUsernameException;

@RestControllerAdvice
public class GlobalExceptionHandler
{
	 @ExceptionHandler(InvalidUsernameException.class)
	 public ErrorResponse invalidUsernameExceptionHandler(InvalidUsernameException e) {
		 return ErrorResponse.create
				 			(e, 
				 			HttpStatusCode.valueOf(400), 
				 			e.getMessage()); 
	 }
}
