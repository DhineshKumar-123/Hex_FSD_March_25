package com.springboot.rest_api.config;

import java.io.IOException;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.exception.InvalidUsernameException;

@RestControllerAdvice
public class GlobalExceptionHandlerConfig //This is to prevent the developer to use try catch block while checking whether id valid or user exist in db already or not it is quite useful for the developers
{
	@ExceptionHandler(InvalidIDException.class)
	public ErrorResponse invalidIDExceptionHandler(InvalidIDException e)
	{
		return ErrorResponse.create(e,HttpStatusCode.valueOf(400),e.getMessage());
	}
	@ExceptionHandler(InvalidUsernameException.class)
	public ErrorResponse invalidUsernameExceptionHandler(InvalidUsernameException e)
	{
		return ErrorResponse.create(e,HttpStatusCode.valueOf(400),e.getMessage());
	}
	@ExceptionHandler(IOException.class)
	public ErrorResponse invalidIOExceptionHandler(IOException e)
	{
		return ErrorResponse.create(e,HttpStatusCode.valueOf(400),e.getMessage());
	}
	@ExceptionHandler(RuntimeException.class)
	public ErrorResponse invalidFileFormatExceptionHandler(RuntimeException e)
	{
		return ErrorResponse.create(e,HttpStatusCode.valueOf(400),e.getMessage());
	}
	@ExceptionHandler(Exception.class)
	 public ErrorResponse exceptionHandler(Exception e) {
		 return ErrorResponse.create
				 			(e, 
				 			HttpStatusCode.valueOf(400), 
				 			e.getMessage()); 
	 }

}
