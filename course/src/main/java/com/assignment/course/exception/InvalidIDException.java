package com.assignment.course.exception;

public class InvalidIDException extends RuntimeException
{
	private static final long serialVersionUID = -793193925812975276L;

	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public InvalidIDException(String message) {
		super();
		this.message = message;
	}
}
