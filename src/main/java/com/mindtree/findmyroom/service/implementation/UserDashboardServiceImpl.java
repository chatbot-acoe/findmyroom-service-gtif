package com.mindtree.findmyroom.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.findmyroom.entity.User;

import com.mindtree.findmyroom.exception.InvalidUserException;

import com.mindtree.findmyroom.exception.InvalidUserCredentialsException;

import com.mindtree.findmyroom.exception.PasswordInvalidException;
import com.mindtree.findmyroom.repository.UserDashboardRepository;
import com.mindtree.findmyroom.service.Interface.UserDashboardService;
import com.mindtree.findmyroom.util.UserCredentialValidation;

/**
 * @author M1048973
 *
 */
@Service
public class UserDashboardServiceImpl implements UserDashboardService {

	@Autowired
	UserDashboardRepository userDashboardRepository;

	@Autowired
	UserCredentialValidation userValidation;

	@Override
	public User getUserDetailsService(String userEmail) throws InvalidUserCredentialsException {

		if(userValidation.isEmailValid(userEmail))
		{
		User user = userDashboardRepository.findUserByUserEmailParam(userEmail);// fetching data using email
		return user;
		}
		else {
			throw new InvalidUserCredentialsException("Invalid update Credentials");
		}
	}

	@Override
	public void updateUserDetailsService(String userEmail, Long userPhone, String userCity, String userStreet,
			String postalCode) throws InvalidUserException,InvalidUserCredentialsException {
		if(userValidation.isEmailValid(userEmail) && userValidation.isPhoneValid(userPhone) && userValidation.isCityValid(userCity) && userValidation.isStreetValid(userStreet)) {
		User user = userDashboardRepository.findUserByUserEmailParam(userEmail);// fetching data using email
		if(user==null)
		{
			throw new InvalidUserException("not able to get data");
		}
		user.setUserPhone(userPhone);// set new phone no
		user.setUserCity(userCity);
		user.setUserStreet(userStreet);
		user.setPostalCode(postalCode);
		userDashboardRepository.save(user);// sending updated fields
		}
		else
			throw new InvalidUserCredentialsException("Invalid update Credentials");
	}

	@Override
	public void updateUserPasswordService(String userEmail, String oldPassword, String newPassword)
			throws PasswordInvalidException, InvalidUserCredentialsException {

		if (userValidation.isPasswordValid(newPassword)&&userValidation.isEmailValid(userEmail)&&userValidation.isPasswordValid(oldPassword)) {
			User user = userDashboardRepository.findUserByUserEmailAndUserPasswordParams(userEmail, oldPassword);// fetching
																													// data
																													// using
																													// email

			if (user == null) {
				throw new PasswordInvalidException("Entered password is wrong");
			} else {
				user.setUserPassword(newPassword);// new password

				userDashboardRepository.save(user);// sending updated fields
			}
		}
		else
			throw new InvalidUserCredentialsException("Invalid Password");

	}

}
