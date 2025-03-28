package com.springboot.rest_api.exception;

public class InvalidContactException extends RuntimeException
{
	private static final long serialVersionUID = 5079167580673812576L;

	private String message;
	public InvalidContactException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
}