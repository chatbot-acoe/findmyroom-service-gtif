package com.mindtree.findmyroom.service.implementation;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.findmyroom.dto.PgDTO;
import com.mindtree.findmyroom.entity.PGRent;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.forgotpasswordrepository.ForgotPasswordRepository;
import com.mindtree.findmyroom.repository.PgRentRepository;
import com.mindtree.findmyroom.repository.PgRepository;
import com.mindtree.findmyroom.repository.UserRepository;
import com.mindtree.findmyroom.service.Interface.PgRentService;

@Service
public class PgRentServiceImpl implements PgRentService {

	@Autowired
	PgRentRepository pgRentRepository;
	
	@Autowired
	ForgotPasswordRepository frpo;
	
	@Autowired
	PgRepository pgRepository;
	@Autowired
	UserRepository urpo;
	public String rentPg(PgDTO pg, String userEmail) {
		PGRent pgRent = new PGRent();
		PayingGuest payingGuest = new PayingGuest();
		payingGuest = pgRepository.getPgById(pg.getPgID());
		if(payingGuest.getOneSharingBeds()==0)
		{
			payingGuest.setTwoSharingBeds(payingGuest.getTwoSharingBeds()-1);
			pgRepository.save(payingGuest);
		}
		else
		{
			payingGuest.setOneSharingBeds(payingGuest.getOneSharingBeds()-1);
			pgRepository.save(payingGuest);
		}
		User customer = new User();

		
		customer = frpo.getUser(userEmail);
		
		User owner = new User();
		owner = frpo.getOwner(payingGuest.getUser().getUserId());
		pgRent.setCustomer(customer);
		pgRent.setDateOfBooking(LocalDate.now());
		pgRent.setPartner(owner);
		pgRent.setPayinGuest(payingGuest);
		pgRentRepository.save(pgRent);
		return "abc";
	}

}
