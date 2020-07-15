package com.mindtree.findmyroom.controller;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.service.Interface.UserService;
import com.mindtree.findmyroom.userdto.UserDto;
/*@RunWith(SpringRunner.class)
@WebMvcTest(value =UserController.class, secure = false)*/
public class UserControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	@Mock
	@Autowired
	private UserService userservice;
	@InjectMocks
    private UserController usercontroller ;
	User user;
	User user1;
	@Spy
	List<User> listuser=new ArrayList<User>();
	@Before
	public void before() {
    	MockitoAnnotations.initMocks(this);
    	mockMvc=MockMvcBuilders.standaloneSetup(usercontroller).build();
    	user=new User();
		user.setUserId(1);
		user.setPostalCode("112334");
		user.setUserCity("bangalo");
		user.setUserEmail("mmm.murali5@gmail.com");
		user.setUserFirstName("mahesh babu");
		user.setUserLastName("mahesh babu");
		user.setUserPassword("Manni@1234");
		user.setUserPhone(1234512345);
		user.setUserReferral("1234");
		user.setUserStreet("btm");
		user.setUserType("partner");
		user.setUserWallet(0);
		
		user1=new User();
		user1.setUserId(7);
		user1.setPostalCode("560004");
		user1.setUserCity("bangaluru");
		user1.setUserEmail("pranav.vajapevam@gmail.com");
		user1.setUserFirstName("pranav");
		user1.setUserLastName("pranav");
		user1.setUserPassword("123456Aa@");
		user1.setUserPhone(760884613);
		user1.setUserReferral("73zknm");
		user1.setUserStreet("chamrajpet");
		user1.setUserType("partner");
		user1.setUserWallet(0);
		listuser.add(user);
		listuser.add(user1);
		
    }
	public static String asJsonString(final Object obj)
	{
		
			try {
				return new ObjectMapper().writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			}
		
	}
	@Test
	public void testUserRegistration() throws Exception
	{

		mockMvc.perform(post("/users/signup")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(user)))
	            .andExpect(status().isOk());

	}
    @Test
	public void testUserLogin() throws Exception {
		List<UserDto> userdtolist=new ArrayList<UserDto>();
		UserDto userdto=new UserDto();
		userdto=new UserDto();
		userdto.setUserEmail("pranav.vajapevam@gmail.com");
		userdto.setUserPassword("123456Aa@");
		userdtolist.add(userdto);

		
		mockMvc.perform(post("/users/login")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(userdto)))
	            .andExpect(status().isOk());
		
	}

}
