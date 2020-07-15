package com.mindtree.findmyroom.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.exception.InvalidEmailException;
import com.mindtree.findmyroom.exception.InvalidUserCredentialsException;
import com.mindtree.findmyroom.exception.PasswordInvalidException;
import com.mindtree.findmyroom.exception.UserExistsException;
import com.mindtree.findmyroom.service.Interface.UserService;
import com.mindtree.findmyroom.userdto.UserDto;

/**
 * 	
 * @author M1049185
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/signup")
	public ResponseEntity<String> userRegistration(@RequestBody User user){
		try {
			userService.registerUser(user);	//calling register user to signup of the user
		} catch (UserExistsException | InvalidUserCredentialsException e) {
			return new ResponseEntity<>("fail",HttpStatus.CONFLICT);	//If the user is already registered for the particular mail-id throw error saying it as a registered mail
		}
		
		try {
			userService.successRegistration(user.getUserEmail());
		} catch (EmailException e) {
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>("success",HttpStatus.OK);			//Returns a success message on successful registration 
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<String> userLogin(@RequestBody UserDto user){		/*authenticates the user 
																			on success returns a role of the user whether he is a Customer or a Partner*/
		String userRole;
		
		System.out.println(user.getUserEmail() + " " + user.getUserPassword());
		try {
			
			userRole = userService.authenticateUser(user);
			
		} catch (InvalidEmailException e) {
			return new ResponseEntity<String>("Invalid Credentials",HttpStatus.BAD_REQUEST);
			
		} catch(PasswordInvalidException e) {
			return new ResponseEntity<String>("Invalid Password", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(userRole, HttpStatus.OK);
		
	}
	
	
	

}
