package com.mindtree.findmyroom.exception;

@SuppressWarnings("serial")
public class InvalidEmailException extends InvalidUserException {

	public InvalidEmailException(String message) {
		super(message);
	}
	 public InvalidEmailException(String message , Exception e)
	 {
		 super(message,e);
	 }

}
