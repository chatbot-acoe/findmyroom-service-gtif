package com.mindtree.findmyroom.exception;

@SuppressWarnings("serial")
public class InvalidUserException extends Exception {

	public InvalidUserException(String message) {
		super(message);
	}

	public InvalidUserException(String message, Exception e) {
	}
		
	

}
