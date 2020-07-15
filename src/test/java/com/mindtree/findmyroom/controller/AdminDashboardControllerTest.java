package com.mindtree.findmyroom.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.service.implementation.AdminDashboardServiceImpl;



public class AdminDashboardControllerTest {

	private MockMvc mockmvc;
	
	@Mock
	@Autowired
	private AdminDashboardServiceImpl service;
	
	@InjectMocks
	private AdminDashboardController controller;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		mockmvc=MockMvcBuilders
				.standaloneSetup(controller)
				.build();
				
	}
	
	@Test
	public void getPartnerDetailsTestCase() throws Exception{
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
		
        when(service.getPartnerDetails()).thenReturn(list);
		
		mockmvc.perform(get("/adminDashboard/getPartnerDetails"))
		.andExpect(status().isOk())
       .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		assertEquals(controller.getUserDetails().size(),3);
		
	}
	
	@Test
	public void removePartnerTestCase() throws Exception
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
		
		 when(service.removePartner(user.getUserId())).thenReturn(true);
			mockmvc.perform(delete("/adminDashboard/deletePartner/{userId}",user.getUserId()))
			.andExpect(status().isOk())
		       .andExpect(content().string("removed sucessfully"));

		
	}
	
	@Test
	public void removePartnerTestCaseNegativr() throws Exception
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
		
		 when(service.removePartner(user.getUserId())).thenReturn(false);
			mockmvc.perform(delete("/adminDashboard/deletePartner/{userId}",user.getUserId()))
			.andExpect(status().isBadRequest())
		       .andExpect(content().string("fail"));

	}
	@Test
	public void verifyPartnerTestCase() throws Exception 
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
		
		
		 when(service.verifyPartnerService(user.getUserId())).thenReturn(user);
		 
		 try {
			mockmvc.perform(put("/adminDashboard/verifyPartner/{userId}",user.getUserId()))
				.andExpect(status().isOk())
			       .andExpect(content().string("partner verified"));
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
		 
	}
	@Test
	public void verifyPartnerTestCaseNegative() throws Exception
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
		
		
		
		 
		 try {
			mockmvc.perform(put("/adminDashboard/verifyPartner/{userId}",1))
				.andExpect(status().isBadRequest())
			       .andExpect(content().string("fail"));
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
	}
	
}



