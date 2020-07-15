package com.mindtree.findmyroom.service.implementation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.exception.InvalidEmailException;
import com.mindtree.findmyroom.exception.InvalidUserCredentialsException;
import com.mindtree.findmyroom.exception.PasswordInvalidException;
import com.mindtree.findmyroom.exception.UserExistsException;
import com.mindtree.findmyroom.repository.UserRepository;
import com.mindtree.findmyroom.userdto.UserDto;
import com.mindtree.findmyroom.util.UserCredentialValidation;

public class UserServiceImplTest {
	
//	@Autowired
//	UserCredentialValidation userValidation ;
//	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserCredentialValidation uservalidation;

	@Spy
	List<User> userList = new ArrayList<>();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		
	}

	@Test
	public void testRegisterUser() throws UserExistsException, InvalidUserCredentialsException {
		User user = new User();
		user.setUserFirstName("Akshay");
		user.setUserLastName("Ranga");
		user.setUserEmail("akshayranga9478@gmail.com");
		user.setUserPassword("12345Aa@");
		user.setUserPhone(7760884613L);
		user.setUserCity("Bengaluru");
		user.setPostalCode("560002");
		user.setUserStreet("20 MS LANE");
		user.setUserType("customer");
		System.out.println(user.toString());
		System.out.println(uservalidation.isFirstNameValid(user.getUserFirstName()));
		when(uservalidation.isFirstNameValid(user.getUserFirstName())).thenReturn(true);
		when(uservalidation.isLastNameValid(user.getUserLastName())).thenReturn(true);
		when(uservalidation.isEmailValid(user.getUserEmail())).thenReturn(true);
		when(uservalidation.isPasswordValid(user.getUserPassword())).thenReturn(true);
		when(uservalidation.isPhoneValid(user.getUserPhone())).thenReturn(true);
		when(uservalidation.isCityValid(user.getUserCity())).thenReturn(true);
		when(uservalidation.isPostalCodeValid(user.getPostalCode())).thenReturn(true);
		when(uservalidation.isStreetValid(user.getUserStreet())).thenReturn(true);
		when(uservalidation.isUserTypeValid(user.getUserType())).thenReturn(true);
		
		assertEquals("Registered Successfully", userService.registerUser(user));

	}
	
	@Test
	public void testAuthenticateUser() throws PasswordInvalidException, InvalidEmailException {
		
		UserDto userDto = new UserDto("akshauranga9478@gmail.com", "12345Aa@");
		
		User user = new User();
		user.setUserFirstName("Akshay");
		user.setUserLastName("Ranga");
		user.setUserEmail("akshayranga9478@gmail.com");
		user.setUserPassword("12345Aa@");
		user.setUserPhone(7760884613L);
		user.setUserCity("Bengaluru");
		user.setPostalCode("560002");
		user.setUserStreet("20 MS LANE");
		user.setUserType("customer");
		
		userList.add(user);
		when(userRepository.validEmail(userDto.getUserEmail())).thenReturn(userList);
		//when(userList.size()).thenReturn(1);
		when(userRepository.userPassword(userDto.getUserEmail())).thenReturn(userDto.getUserPassword());
		when(userRepository.getRole(userDto.getUserEmail())).thenReturn("customer");
		assertEquals("customer",userService.authenticateUser(userDto));
		
	}
	
//	@Test
//	public void testEmail() throws EmailException {
//		userService.successRegistration("aksh@gmail.com");
//		
//	}
	
	@Test(expected = InvalidEmailException.class)
	public void authenticateException() throws PasswordInvalidException, InvalidEmailException {
		UserDto userDto = new UserDto("akshauranjdjdjga9478@gmail.com", "12345Aa@");
		userService.authenticateUser(userDto);
	}
	
	@Test(expected = InvalidUserCredentialsException.class)
	public void registeredEmail() throws UserExistsException, InvalidUserCredentialsException  {
		User user = new User();
		user.setUserFirstName("Akshay");
		user.setUserLastName("Ranga");
		user.setUserEmail("akshayranga9478@gmail.com");
		user.setUserPassword("12345Aa@");
		user.setUserPhone(7760884613L);
		user.setUserCity("Bengaluru");
		user.setPostalCode("560002");
		user.setUserStreet("20 MS LANE");
		user.setUserType("customer");
		userService.registerUser(user);
		
		

	}
	
	
	
	
	


}
