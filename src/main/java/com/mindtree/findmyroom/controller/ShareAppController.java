package com.mindtree.findmyroom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.findmyroom.entity.FlatBuyDto;
import com.mindtree.findmyroom.entity.PGRentDto;
import com.mindtree.findmyroom.exception.InvalidEmailException;
import com.mindtree.findmyroom.exception.UserExistsException;
import com.mindtree.findmyroom.service.Interface.SendRefferedEmail;

@RestController
@RequestMapping("/refer")
@CrossOrigin(origins = "*")
public class ShareAppController {
	
	@Autowired
	SendRefferedEmail sri;
	
	
	@PostMapping("/{userName}/{userEmail}/{userReferral}/{referredEmailId}")
	public ResponseEntity<String> referemail(@PathVariable String userName,@PathVariable String userEmail, @PathVariable String userReferral , @PathVariable String referredEmailId)
	{
		try
		{
			sri.sendmail(userName,userEmail,userReferral,referredEmailId);
		}catch (InvalidEmailException e) { 
			
			return new ResponseEntity<String>("invalid email", HttpStatus.BAD_REQUEST); 
		
		}catch(UserExistsException e)
		{
		return new ResponseEntity<String>("mail already",HttpStatus.BAD_REQUEST);	
		}
		return new ResponseEntity<String>("email sent", HttpStatus.OK);
	
				
	}

	@GetMapping("/flat/{userId}")
public List<FlatBuyDto> getbookindetails(@PathVariable int userId)
{
		List<FlatBuyDto> fla = new ArrayList<>();
		List<FlatBuyDto> flatbuy = sri.getbookindetailsf(userId);
		if(flatbuy.isEmpty())
		{
			return fla;
		}
		else
		{
				return flatbuy;
		}
}
	@GetMapping("/pg/{userId}")
	public List<PGRentDto> getbookindetailsp(@PathVariable int userId)
	{	
		List<PGRentDto> pgren = new ArrayList<>();
			List<PGRentDto> pgrent = sri.getbookindetailsp(userId);
			if(pgrent.isEmpty())
			{
				return pgren;
			}
			else
			{
					return pgrent;
			}
	}
}
