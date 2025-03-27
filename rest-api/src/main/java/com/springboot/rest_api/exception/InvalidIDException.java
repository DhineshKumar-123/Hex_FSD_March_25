package com.springboot.rest_api.exception;

public class InvalidIDException extends RuntimeException
{
	private static final long serialVersionUID = 5079167580673812576L;

	private String message;
	public InvalidIDException(String message) {
		super();
		this.message = message;
	}
}
