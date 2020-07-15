package com.mindtree.findmyroom.service.Interface;

import org.apache.commons.mail.EmailException;

import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.exception.InvalidEmailException;
import com.mindtree.findmyroom.exception.InvalidUserCredentialsException;
import com.mindtree.findmyroom.exception.PasswordInvalidException;
import com.mindtree.findmyroom.exception.UserExistsException;
import com.mindtree.findmyroom.userdto.UserDto;

public interface UserService  {
	
	public String registerUser(User user) throws UserExistsException, InvalidUserCredentialsException;			//Register a user to the web-portal
	public String authenticateUser(UserDto user) throws PasswordInvalidException, InvalidEmailException;	//verify whether a user is registered or not for login purpose 
	public void successRegistration(String toEmail) throws EmailException;
}
