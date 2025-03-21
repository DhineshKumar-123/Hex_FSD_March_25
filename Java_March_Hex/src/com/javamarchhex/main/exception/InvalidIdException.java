package com.javamarchhex.main.exception;

public class InvalidIdException extends Exception
{
	private static final long serialVersionUID = 3730653930023755472L;// This for our Protection purpose
	
	private String message;

	public InvalidIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
