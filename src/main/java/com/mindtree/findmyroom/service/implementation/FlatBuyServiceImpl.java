package com.mindtree.findmyroom.service.implementation;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.findmyroom.entity.Flat;
import com.mindtree.findmyroom.entity.FlatBuy;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.flatdto.FlatDetails;
import com.mindtree.findmyroom.forgotpasswordrepository.ForgotPasswordRepository;
import com.mindtree.findmyroom.repository.FlatBuyRepository;
import com.mindtree.findmyroom.repository.FlatRepository;
import com.mindtree.findmyroom.repository.UserRepository;
import com.mindtree.findmyroom.service.Interface.FlatBuyService;

@Service
public class FlatBuyServiceImpl implements FlatBuyService {

	@Autowired
	FlatBuyRepository flatBuyRepository;
	@Autowired
	ForgotPasswordRepository frpo;
	
	@Autowired
	FlatRepository flatRepository;
	@Autowired
	UserRepository urpo;
	
	public String buyflat(FlatDetails flat, String userEmail) {
		FlatBuy flatBuy = new FlatBuy();
		Flat flatt = new Flat();
		flatt = flatRepository.getFlatDetails(flat.getId());
		flatt.setAvailable(false);
		flatRepository.save(flatt);
		
		User customer = new User();
		customer = frpo.getUser(userEmail);
		
		User owner = new User();
		owner = frpo.getOwner(flatt.getUser().getUserId());
		
		flatBuy.setCustomer(customer);
		flatBuy.setDateOfBooking(LocalDate.now());
		flatBuy.setFlat(flatt);
		flatBuy.setPartner(owner);
		flatBuy.setType(flat.getFaltType());
		flatBuyRepository.save(flatBuy);
		return "abc";
	}

}
