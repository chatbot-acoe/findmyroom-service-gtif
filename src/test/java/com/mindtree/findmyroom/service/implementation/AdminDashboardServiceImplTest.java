package com.mindtree.findmyroom.service.implementation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.mindtree.findmyroom.entity.Flat;
import com.mindtree.findmyroom.entity.FlatBuy;
import com.mindtree.findmyroom.entity.PGRent;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.flatdto.FlatDetails;
import com.mindtree.findmyroom.repository.AdminDashboardRepository;
import com.mindtree.findmyroom.repository.FlatBuyRepository;
import com.mindtree.findmyroom.repository.FlatRepository;
import com.mindtree.findmyroom.repository.PgRentRepository;
import com.mindtree.findmyroom.repository.PgRepository;

public class AdminDashboardServiceImplTest {

	@Mock
	AdminDashboardRepository adminDashboardRepository;
	
	@Mock
	FlatBuyRepository flatBuyRepository;
	
	@Mock
	FlatRepository flatRepository;
	
	@Mock
	PgRepository pgRepository;
	
	@Mock
	PgRentRepository pgRentRepository;
	
	@InjectMocks
	AdminDashboardServiceImpl service;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getPartnerDetailsTestCase()
	{
		List<User> list=getListPartner();
		when(adminDashboardRepository.findUserByUserType("partner")).thenReturn(list);
		assertEquals(3,service.getPartnerDetails().size());
	}
	
	@Test
	public void verifyPartnerTestCase()
	{
		User user=new User();
		user.setUserEmail("Sansri123@gmail.com");
		user.setUserFirstName("sanjali");
		user.setPostalCode("123456");
		user.setUserCity("bangalore");
		user.setUserId(1234556);
		user.setUserPassword("123Aa@");
		user.setUserId(123456);
		user.setUserLastName("sri");
		user.setUserPhone(1234567890);
		user.setUserReferral("rdv@12");
		user.setUserStreet("BTM");
		user.setUserType("partner");
		user.setUserWallet(12);
		
		when(adminDashboardRepository.getOne(user.getUserId())).thenReturn(user);
		assertEquals(user,service.verifyPartnerService(user.getUserId()));
		
	}
	
	@Test
	public void verifyPartnerTestCaseNegative()
	{
		User user=new User();
		user.setUserEmail("Sansri123@gmail.com");
		user.setUserFirstName("sanjali");
		user.setPostalCode("123456");
		user.setUserCity("bangalore");
		user.setUserId(1234556);
		user.setUserPassword("123Aa@");
		user.setUserId(123456);
		user.setUserLastName("sri");
		user.setUserPhone(1234567890);
		user.setUserReferral("rdv@12");
		user.setUserStreet("BTM");
		user.setUserType("partner");
		user.setUserWallet(12);
		
		when(adminDashboardRepository.getOne(user.getUserId())).thenReturn(null);
		assertEquals(null,service.verifyPartnerService(user.getUserId()));
		
	}
	
	
//	@Test
//	public void sucessEmail()
//	{
//		Email email = new SimpleEmail();
//		email.setHostName("smtp.googlemail.com");
//		email.setSmtpPort(587);
//		email.setAuthenticator(new DefaultAuthenticator("findmyroom07@gmail.com", "gandhi@JI"));
//		email.setStartTLSEnabled(true);
//		try {
//			email.setFrom("findmyroom07@gmail.com");
//		} catch (EmailException e1) {
//			
//			e1.printStackTrace();
//		}
//		email.setSubject("Verification mail");
//		try {
//			email.setMsg(
//					"Congrats!!!!!!!\nNow you are a verified partner. Expand your business easily with us as a verified partner.\nThank you for choosing us..\n");
//		} catch (EmailException e) {
//			
//			e.printStackTrace();
//		}
//		try {
//			email.addTo("Sansri@gmail.com");
//		} catch (EmailException e) {
//		
//			e.printStackTrace();
//		}
//		try {
//			email.send();
//		} catch (EmailException e) {
//			
//			e.printStackTrace();
//		}
//		
//		try {
//			service.successVerify("Sansri@gmail.com");
//		} catch (EmailException e) {
//			
//			e.printStackTrace();
//		}
//	}
	
	
	@Test
	public void removePartnerTest() {
		
		User user=new User();
		user.setUserEmail("Sansri123@gmail.com");
		user.setUserFirstName("sanjali");
		user.setPostalCode("123456");
		user.setUserCity("bangalore");
		user.setUserId(1234556);
		user.setUserPassword("123Aa@");
		user.setUserId(123456);
		user.setUserLastName("sri");
		user.setUserPhone(1234567890);
		user.setUserReferral("rdv@12");
		user.setUserStreet("BTM");
		user.setUserType("partner");
		user.setUserWallet(12);
		
	service.removePartner(user.getUserId());
	service.removePartner(2);
		
		
	}
	
	public List<User> getListPartner()
	{
		List<User> list=new ArrayList<User>();
		User user=new User();
		user.setUserEmail("Sansri123@gmail.com");
		user.setUserFirstName("sanjali");
		user.setPostalCode("123456");
		user.setUserCity("bangalore");
		user.setUserId(1234556);
		user.setUserPassword("123Aa@");
		user.setUserId(123456);
		user.setUserLastName("sri");
		user.setUserPhone(1234567890);
		user.setUserReferral("rdv@12");
		user.setUserStreet("BTM");
		user.setUserType("partner");
		user.setUserWallet(12);
		list.add(user);
		user.setUserEmail("Vv12345@gmail.com");
		user.setUserFirstName("vv");
		user.setPostalCode("123456");
		user.setUserCity("bangalore");
		user.setUserId(1234554);
		user.setUserPassword("123Aa@");
		user.setUserId(123456);
		user.setUserLastName("sri");
		user.setUserPhone(1234567890);
		user.setUserReferral("rdv@12");
		user.setUserStreet("jayanagr");
		user.setUserType("partner");
		user.setUserWallet(12);
		list.add(user);
		user.setUserEmail("Aa12345@gmail.com");
		user.setUserFirstName("vv");
		user.setPostalCode("123456");
		user.setUserCity("bangalore");
		user.setUserPassword("123Aa@");
		user.setUserId(123455);
		user.setUserLastName("sri");
		user.setUserPhone(1234567890);
		user.setUserReferral("rdv@12");
		user.setUserStreet("jayanagr");
		user.setUserType("partner");
		user.setUserWallet(12);
		list.add(user);
		return list;
	}
}
