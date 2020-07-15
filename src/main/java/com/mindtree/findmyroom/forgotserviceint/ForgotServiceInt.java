package com.mindtree.findmyroom.forgotserviceint;

import com.mindtree.findmyroom.exception.InvalidEmailException;

public interface ForgotServiceInt 
{
	public String CheckUser(String username) throws InvalidEmailException;	//verify whether a user is registered or not for sending password
	
}
