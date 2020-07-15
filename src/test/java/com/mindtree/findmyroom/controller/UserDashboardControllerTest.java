package com.mindtree.findmyroom.controller;


import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.mindtree.findmyroom.service.implementation.UserDashboardServiceImpl;


public class UserDashboardControllerTest {

	private MockMvc mockmvc;
	
	@Mock
	@Autowired
	private UserDashboardServiceImpl service;
	
	@InjectMocks
	private UserDashboardController controller;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		mockmvc=MockMvcBuilders
				.standaloneSetup(controller)
				.build();
				
	}

	@Test
	public void userDetailsControllerTest() throws Exception
	{
		User user=new User();
		user.setUserEmail("Sansri123@gmailcom");
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
		user.setUserType("customer");
		user.setUserWallet(12);
		
		when(service.getUserDetailsService("Sansri123@gmailcom")).thenReturn(user);
		
		mockmvc.perform(get( "/userDashboard/getUserDetails/{userEmail}","Sansri123@gmailcom"))
		.andExpect(status().isOk())
       .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		assertEquals(controller.getUserDetails("Sansri123@gmailcom").getUserEmail(), user.getUserEmail());
	
	}
	
	
	
	@Test
	public void updatePasswordControllerTest() throws Exception
	{
		User user=new User();
		user.setUserEmail("Sansri123@gmailcom");
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
		user.setUserType("customer");
		user.setUserWallet(12);
		
		
		mockmvc.perform(put( "/userDashboard/updatePassword/{userEmail}/{oldpassword}/{newpassword}","Sansri123@gmailcom","123Aa@","1234Bb@"))
		.andExpect(status().isOk())
	       .andExpect(content().string("pass"));

   
    
	}
	
	
	@Test
	public void updatedetailsControllerTest() throws Exception
	{
		User user=new User();
		user.setUserEmail("Sansri123@gmailcom");
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
		user.setUserType("customer");
		user.setUserWallet(12);
		
		
		mockmvc.perform(put( "/userDashboard/updateUserDetails/{userEmail}/{userPhone}/{userCity}/{userStreet}/{postalCode}","Sansri123@gmailcom",1234567890,"pune","punecity","123456"))
		.andExpect(status().isOk())
	       .andExpect(content().string("updated sucessfully"));

   
    
	}
}
