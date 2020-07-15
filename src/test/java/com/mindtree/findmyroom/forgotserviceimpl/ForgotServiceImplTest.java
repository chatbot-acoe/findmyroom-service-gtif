package com.mindtree.findmyroom.forgotserviceimpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.exception.InvalidEmailException;
import com.mindtree.findmyroom.forgotpasswordrepository.ForgotPasswordRepository;

public class ForgotServiceImplTest {
	@Mock
	private ForgotPasswordRepository frp;
	
	@InjectMocks
	private ForgotServiceImpl forgotservicei;
	
	@Spy
	private List<User> users = new ArrayList<>();
	
	@Before
	public void testSetup() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		users = getusers();
		}
	
	private List<User> getusers() {
		List<User> userlist = new ArrayList<>();
		User user = new User();
		user.setUserEmail("abc@gmail.com");
		user.setUserPassword("abc");
		User user2 = new User();
		user2.setUserEmail("abcd@gmail.com");
		user2.setUserPassword("abcd");
		User user3 = new User();
		user3.setUserEmail("abcde@gmail.com");
		user3.setUserPassword("abcde");
		User user4 = new User();
		user4.setUserEmail("abcdef@gmail.com");
		user4.setUserPassword("abcdef");
		userlist.add(user);
		userlist.add(user2);
		userlist.add(user3);
		userlist.add(user4);
		
		return userlist;
	}

	@Test
	public void checkusernametest()  {
		//fail("Not yet implemented");
		when(frp.validEmail("abc@gmail.com")).thenReturn(users.get(0).getUserEmail());
		when(frp.userPassword("abc@gmail.com")).thenReturn(users.get(0).getUserPassword());
		
		
			
				try {
					assertEquals(forgotservicei.CheckUser("abc@gmail.com"),"password sent successfully as email");
				} catch (InvalidEmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	}
	@Test
	public void checkusernametestinv()  {
		//fail("Not yet implemented");
		when(frp.validEmail("abc@gmail.com")).thenReturn(users.get(0).getUserEmail());
		when(frp.userPassword("abc@gmail.com")).thenReturn(users.get(0).getUserPassword());
		
		
			
				try {
					assertNotEquals(forgotservicei.CheckUser("abc@gmail.com"),"password successfully as email");
				} catch (InvalidEmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	}
	@Test
	public void checkusernametestinvalid()  {
		//fail("Not yet implemented");
		when(frp.validEmail("abc")).thenReturn("abc");
		when(frp.userPassword("abc")).thenReturn("c");
		
		
			
				try {
					assertEquals(forgotservicei.CheckUser("aqbc@gmail.com"),"Invalid");
				} catch (InvalidEmailException e) {
					// TODO Auto-generated catch block
					System.out.println("invalid");
				}
			
		
	}
	@Test
	public void checkusernametest2()  {
		//fail("Not yet implemented");
		when(frp.validEmail("abcd@gmail.com")).thenReturn(users.get(1).getUserEmail());
		when(frp.userPassword("abcd@gmail.com")).thenReturn(users.get(1).getUserPassword());
		
		
			
				try {
					assertEquals(forgotservicei.CheckUser("abcd@gmail.com"),"password sent successfully as email");
				} catch (InvalidEmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	}
	@Test
	public void checkusernametest3()  {
		//fail("Not yet implemented");
		when(frp.validEmail("abcde@gmail.com")).thenReturn(users.get(2).getUserEmail());
		when(frp.userPassword("abcde@gmail.com")).thenReturn(users.get(2).getUserPassword());
		
		
			
				try {
					assertEquals(forgotservicei.CheckUser("abcde@gmail.com"),"password sent successfully as email");
				} catch (InvalidEmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	}
	@Test
	public void checkusernametest4()  {
		//fail("Not yet implemented");
		when(frp.validEmail("abcdef@gmail.com")).thenReturn(users.get(3).getUserEmail());
		when(frp.userPassword("abcdef@gmail.com")).thenReturn(users.get(3).getUserPassword());
		
		
			
				try {
					assertEquals(forgotservicei.CheckUser("abcdef@gmail.com"),"password sent successfully as email");
				} catch (InvalidEmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	}
	
		
	

}
