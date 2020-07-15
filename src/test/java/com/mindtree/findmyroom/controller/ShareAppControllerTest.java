package com.mindtree.findmyroom.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.findmyroom.service.Interface.SendRefferedEmail;

@RunWith(SpringRunner.class)
@WebMvcTest(value= ShareAppController.class, secure= false)
public class ShareAppControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SendRefferedEmail sri;
	
	@InjectMocks
	private ShareAppController shareAppController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(shareAppController).build();
	}
	
	@Test
	public void testGetbookindetails() throws Exception {
		
		mockMvc.perform(get("/refer//flat/{userId}", 1)).andExpect((ResultMatcher) content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void testGetbookindetailsp() throws Exception {
		mockMvc.perform(get("/refer/pg/{userId}", 1)).andExpect((ResultMatcher) content().contentType("application/json;charset=UTF-8"));
	}

}
