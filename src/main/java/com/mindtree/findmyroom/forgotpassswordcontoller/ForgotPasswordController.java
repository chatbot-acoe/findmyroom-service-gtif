package com.mindtree.findmyroom.forgotpassswordcontoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.findmyroom.exception.InvalidEmailException;
import com.mindtree.findmyroom.forgotserviceint.ForgotServiceInt;
@CrossOrigin("*")
@RestController

public class ForgotPasswordController 
{
	
	@Autowired
	ForgotServiceInt forgot;
	@PostMapping(value ="/sendmail/{userEmailId}")
	public ResponseEntity<String> userLogin(@PathVariable("userEmailId") String userEmailId){		
		
		try
		{
			forgot.CheckUser(userEmailId);
		}
		catch (InvalidEmailException e) {
			
			
			return new ResponseEntity<String>("Invalid Credentials",HttpStatus.BAD_REQUEST);
			
		}
		return new ResponseEntity<String>("Password", HttpStatus.OK);
		
		}
}
