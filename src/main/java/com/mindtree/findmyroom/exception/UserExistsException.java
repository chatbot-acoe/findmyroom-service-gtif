package com.mindtree.findmyroom.exception;

@SuppressWarnings("serial")
public class UserExistsException extends InvalidUserException {

	public UserExistsException(String message) {
		super(message);
	}

}
