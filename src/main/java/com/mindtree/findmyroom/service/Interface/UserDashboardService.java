package com.mindtree.findmyroom.service.Interface;

import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.exception.InvalidUserCredentialsException;
import com.mindtree.findmyroom.exception.InvalidUserException;
import com.mindtree.findmyroom.exception.PasswordInvalidException;

public interface UserDashboardService {

	public User getUserDetailsService(String userEmail) throws InvalidUserCredentialsException;//method to get details

	public void updateUserPasswordService(String userEmail,String oldPassword,String newPassword) throws PasswordInvalidException, InvalidUserCredentialsException;//method to update password
	public void updateUserDetailsService(String userEmail,Long userPhone,String userCity,String userStreet,String postalCode) throws InvalidUserCredentialsException, InvalidUserException;//method to edit profile

	
}