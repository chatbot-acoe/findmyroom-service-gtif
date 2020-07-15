package com.mindtree.findmyroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.findmyroom.flatdto.FlatDetails;
import com.mindtree.findmyroom.service.implementation.FlatBuyServiceImpl;

@RestController
@RequestMapping("/FlatBuy")
@CrossOrigin(origins="*")
public class FlatBuyController {

	@Autowired
	FlatBuyServiceImpl flatBuyService;
	
	@PostMapping("/flat/{userEmail}")
	public void buyflat(@PathVariable String userEmail,@RequestBody FlatDetails flat)
	{
		
		flatBuyService.buyflat(flat,userEmail);
			}
}
