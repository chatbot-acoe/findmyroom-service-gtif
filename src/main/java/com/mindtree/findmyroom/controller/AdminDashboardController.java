package com.mindtree.findmyroom.controller;

import java.util.List;


import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.service.Interface.AdminDashboardService;
import com.mindtree.findmyroom.service.implementation.AdminDashboardServiceImpl;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/adminDashboard")
public class AdminDashboardController {

	
	@Autowired
	AdminDashboardService adminDashBoardService = new AdminDashboardServiceImpl();// object of service layer
	
	
	@GetMapping(value = "/getPartnerDetails") // get partner details method
	public List<User> getUserDetails() {
		

		List<User> partnerList=adminDashBoardService.getPartnerDetails();// calling the method of service
		
		return partnerList;

	}
	@DeleteMapping(value = "/deletePartner/{userId}") // delete partner  method
	public ResponseEntity<String> updateUserDetails(@PathVariable(value="userId")int userId)
	{
		if(adminDashBoardService.removePartner(userId))
		return  new ResponseEntity<String>("removed sucessfully",HttpStatus.OK);
		return  new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		
	}
	
	@PutMapping(value = "/verifyPartner/{userId}")//verify method 
	public ResponseEntity<String> verifyPartnerController(@PathVariable(value="userId")int userId)
	{
		User partner=adminDashBoardService.verifyPartnerService(userId);
		if(partner==null)
		return  new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		try {
			adminDashBoardService.successVerify(partner.getUserEmail());
		} catch (EmailException e) {
			
			return  new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		}
		
		
		return  new ResponseEntity<String>("partner verified",HttpStatus.OK);
		
	}
	
}
