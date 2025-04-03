package com.assignment.course.exception;

public class InvalidUserNameException extends Exception
{

	private static final long serialVersionUID = -793193925812975276L;

	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public InvalidUserNameException(String message) {
		super();
		this.message = message;
	}
}
