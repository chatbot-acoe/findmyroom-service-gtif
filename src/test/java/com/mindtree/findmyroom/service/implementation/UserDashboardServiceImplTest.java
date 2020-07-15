package com.mindtree.findmyroom.service.implementation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.exception.InvalidUserCredentialsException;
import com.mindtree.findmyroom.exception.InvalidUserException;
import com.mindtree.findmyroom.exception.PasswordInvalidException;
import com.mindtree.findmyroom.repository.UserDashboardRepository;
import com.mindtree.findmyroom.util.UserCredentialValidation;


public class UserDashboardServiceImplTest {

	@Mock
private	UserDashboardRepository userdashboardrepository;
	
	@Mock
	private UserCredentialValidation uservalidation;
	
	@InjectMocks
	private UserDashboardServiceImpl userdashboardservice;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getUserByEmailIdTest() 
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
		user.setUserType("customer");
		user.setUserWallet(12);
		
		when(uservalidation.isEmailValid(user.getUserEmail())).thenReturn(true);
		
		when(userdashboardrepository.findUserByUserEmailParam(user.getUserEmail())).thenReturn(user);
		try {
			assertEquals(user,userdashboardservice.getUserDetailsService(user.getUserEmail()));
		} catch (InvalidUserCredentialsException e) {
			
			
		}
		
		
	}
	@Test
	public void getUserByEmailIdTestNegative() 
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
		user.setUserType("customer");
		user.setUserWallet(12);
		
		when(uservalidation.isEmailValid(user.getUserEmail())).thenReturn(false);
		
		when(userdashboardrepository.findUserByUserEmailParam(user.getUserEmail())).thenReturn(null);
		try {
			assertEquals(null,userdashboardservice.getUserDetailsService(user.getUserEmail()));
		} catch (InvalidUserCredentialsException e) {
			
			
		}
	}
	
	
	
	@Test
	public void upadteDetailsTestCase() throws InvalidUserCredentialsException, InvalidUserException
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
		user.setUserType("customer");
		user.setUserWallet(12);
		
		when(uservalidation.isEmailValid(user.getUserEmail())).thenReturn(true);
		when(uservalidation.isPhoneValid(user.getUserPhone())).thenReturn(true);
		when(uservalidation.isStreetValid(user.getUserStreet())).thenReturn(true);
		when(uservalidation.isCityValid(user.getUserCity())).thenReturn(true);
		when(uservalidation.isPostalCodeValid(user.getPostalCode())).thenReturn(true);
		when(userdashboardrepository.findUserByUserEmailParam("Sansri123@gmail.com")).thenReturn(user);
		userdashboardservice.updateUserDetailsService(user.getUserEmail(), user.getUserPhone(), user.getUserCity(), user.getUserStreet(), user.getPostalCode());
		
		
	}
	
	@Test
	public void upadteDetailsTestCaseNegative()
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
		user.setUserType("customer");
		user.setUserWallet(12);
		
		when(uservalidation.isEmailValid(user.getUserEmail())).thenReturn(true);
		when(uservalidation.isPhoneValid(user.getUserPhone())).thenReturn(true);
		when(uservalidation.isStreetValid(user.getUserStreet())).thenReturn(false);
		when(uservalidation.isCityValid(user.getUserCity())).thenReturn(true);
		when(uservalidation.isPostalCodeValid(user.getPostalCode())).thenReturn(true);
		when(userdashboardrepository.findUserByUserEmailParam("Sansri123@gmail.com")).thenReturn(null);
		try {
			userdashboardservice.updateUserDetailsService(user.getUserEmail(), user.getUserPhone(), user.getUserCity(), user.getUserStreet(), user.getPostalCode());
		} catch (InvalidUserException e) {
			
			
		}
		
	}
	
	
	@Test
	public void updatePasswordTestcase()
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
		user.setUserType("customer");
		user.setUserWallet(12);
		
		when(uservalidation.isEmailValid(user.getUserEmail())).thenReturn(true);
		when(uservalidation.isPasswordValid(user.getUserPassword())).thenReturn(true);
		when(uservalidation.isPasswordValid("123Aa@")).thenReturn(true);
		when(userdashboardrepository.findUserByUserEmailAndUserPasswordParams("Sansri123@gmail.com", "123Aa@")).thenReturn(user);
		try {
			userdashboardservice.updateUserPasswordService(user.getUserEmail(), user.getUserPassword(), "123Aa@");
		} catch (PasswordInvalidException | InvalidUserCredentialsException e) {
			
			
		}
		
	}
	
	@Test
	public void updatePasswordTestcaseNegativeEmail()
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
		user.setUserType("customer");
		user.setUserWallet(12);
		
		when(uservalidation.isEmailValid(user.getUserEmail())).thenReturn(false);
		when(uservalidation.isPasswordValid(user.getUserPassword())).thenReturn(false);
		when(uservalidation.isPasswordValid("123Aa@")).thenReturn(true);
		when(userdashboardrepository.findUserByUserEmailAndUserPasswordParams("Sansri123@gmail.com", "123Aa@")).thenReturn(user);
		try {
			userdashboardservice.updateUserPasswordService(user.getUserEmail(), user.getUserPassword(), "123Aa@");
		} 
		 catch (InvalidUserCredentialsException e) {
			
			
		} catch (PasswordInvalidException e) {
			
			
		}
		
	}

}
