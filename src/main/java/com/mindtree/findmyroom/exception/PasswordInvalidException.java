package com.mindtree.findmyroom.exception;

@SuppressWarnings("serial")
public class PasswordInvalidException extends InvalidUserException {

	public PasswordInvalidException(String message) {
		super(message);
	}

}
