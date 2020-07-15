package com.mindtree.findmyroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.findmyroom.entity.User;

import com.mindtree.findmyroom.exception.InvalidUserException;

import com.mindtree.findmyroom.exception.InvalidUserCredentialsException;

import com.mindtree.findmyroom.exception.PasswordInvalidException;
import com.mindtree.findmyroom.service.Interface.UserDashboardService;
import com.mindtree.findmyroom.service.implementation.UserDashboardServiceImpl;

/**
 * @author M1048973
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/userDashboard")
public class UserDashboardController {

	@Autowired
	UserDashboardService userDashBoardService = new UserDashboardServiceImpl();// object of service layer

	@GetMapping(value = "/getUserDetails/{userEmail}") // get user details method
	public User getUserDetails(@PathVariable(value = "userEmail") String userEmail) {
	

		User user;
		try {
			user = userDashBoardService.getUserDetailsService(userEmail);
			user.setUserPassword("xxxxxxxxxx");
			return user;

		} catch (InvalidUserCredentialsException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}

	@PutMapping(value = "/updatePassword/{userEmail}/{oldPassword}/{newPassword}") // method to change password
	public ResponseEntity<String> updatePassword(@PathVariable(value = "userEmail") String userEmail,
			@PathVariable(value = "oldPassword") String oldPassword,
			@PathVariable(value = "newPassword") String newPassword) {
		try {
			userDashBoardService.updateUserPasswordService(userEmail, oldPassword, newPassword);// calling the method of
																								// service

		} catch (PasswordInvalidException e) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);

		} catch (InvalidUserCredentialsException e) {
			return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("pass", HttpStatus.OK);// returning status

	}

	@PutMapping(value = "/updateUserDetails/{userEmail}/{userPhone}/{userCity}/{userStreet}/{postalCode}")//edit profile method 
	public ResponseEntity<String> updateUserDetails(@PathVariable(value="userEmail") String userEmail,@PathVariable(value="userPhone") long userPhone,@PathVariable(value="userCity") String userCity,@PathVariable(value="userStreet") String userStreet,@PathVariable(value="postalCode") String postalCode)
	{

		try {
			userDashBoardService.updateUserDetailsService(userEmail, userPhone, userCity, userStreet, postalCode);
		} catch (InvalidUserCredentialsException e) {
			return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
		} catch (InvalidUserException e) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return  new ResponseEntity<String>("updated sucessfully",HttpStatus.OK);//returning status

	}

}
