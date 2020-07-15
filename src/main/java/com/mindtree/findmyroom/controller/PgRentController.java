package com.mindtree.findmyroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.findmyroom.dto.PgDTO;
import com.mindtree.findmyroom.service.Interface.PgRentService;

@RestController
@RequestMapping("/PGRent")
@CrossOrigin(origins="*")
public class PgRentController {

	@Autowired
	PgRentService pgRentService;
	
	@PostMapping("/pgrent/{userEmail}")
	public void rentPg(@PathVariable String userEmail,@RequestBody PgDTO pg )
	{
		
		pgRentService.rentPg(pg,userEmail);
		}
}
