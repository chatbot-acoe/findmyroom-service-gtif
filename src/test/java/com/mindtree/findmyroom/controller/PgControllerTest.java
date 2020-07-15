package com.mindtree.findmyroom.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.findmyroom.dto.PgDTO;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.exception.InvalidUserException;
import com.mindtree.findmyroom.exception.NoSearchResultForPgException;
import com.mindtree.findmyroom.exception.UnabletoDeleteException;
import com.mindtree.findmyroom.service.implementation.PgServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value= PgController.class, secure= false)
public class PgControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean
	private PgServiceImpl pgService;

	@InjectMocks
	private PgController pgController;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(pgController).build();
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
	public void getAllPgDetailstest() throws Exception {
	
		
		mockMvc.perform(get("/pg/pgdetails/bangalore/KRPuram")).
		andExpect(status().isOk()).andExpect(content().
				contentType("application/json;charset=UTF-8"));
		

	}
	
	@Test
	public void getPgDetailsById() throws Exception  {
		
		
		PgDTO pg= new PgDTO();
		pg.setPgID(1);
		pg.setPgName("Sai sri Ram Pg");
		
		when(pgService.retrievePgDetailsbyId(1)).thenReturn(pg);
	
	mockMvc.perform(get("/pg/pgdetails/{pgId}",1)).andExpect(status().isOk()).
	andExpect(content().contentType("application/json;charset=UTF-8"));
	
	}
	
	@Test
	public void getPgDetailsByIdException1() throws Exception{
		
		PgDTO pg= new PgDTO();
		pg.setPgID(1);
		pg.setPgName("Sai sri Ram Pg");
		
		
		when(pgService.retrievePgDetailsbyId(1)).thenThrow(NoSearchResultForPgException.class);
		assertEquals(new ResponseEntity<PgDTO>(HttpStatus.NO_CONTENT), pgController.getPgDetailsbyId(1));
	}
	
	@Test
	public void getAllPgDetailstestException1() throws Exception{
		
		when(pgService.retrieveAllPgDetails("bangalore", "KRPuram")).thenThrow(NoSearchResultForPgException.class);
		assertEquals(new ResponseEntity<Set<PgDTO>>(HttpStatus.NO_CONTENT), pgController.getAllPgDetails("bangalore", "KRPuram"));
	}
	
	@Test
	public void getPgsOfOwnersException1() throws Exception{
		
		when(pgService.getPgsofPartner("akshayKadham01@gmail.com")).thenThrow(InvalidUserException.class);
		assertEquals(new ResponseEntity<Set<PgDTO>>(HttpStatus.BAD_REQUEST), pgController.getPgsOfOwner("akshayKadham01@gmail.com"));
	}
	
	
	
	@Test
	public void getPgsOfOwners() throws Exception{
		
		Set<PgDTO> set= new HashSet<>();
		PgDTO pg= new PgDTO();
		pg.setPgID(1);
		pg.setPgName("Sai sri Ram Pg");
		set.add(pg);
		
		when(pgService.getPgsofPartner("akshayKadham01@gmail.com")).thenReturn(set);
		
		mockMvc.perform(get("/pg/get/akshayKadham01@gmail.com")).andExpect(status().isNotAcceptable());
		

		
	}
	
	
	
	
	@Test
	public void deletePg() throws Exception {
		String msg="deleted successfully";
		PgDTO pg= new PgDTO();
		pg.setPgID(1);
		
		when(pgService.retrievePgDetailsbyId(1)).thenReturn(pg);
		
		when(pgService.deletePg("akshayKadham01@gmail.com", 1)).thenReturn(msg);
		
		mockMvc = MockMvcBuilders.standaloneSetup(pgController).build();
		mockMvc.perform(delete("/pg/delete/akshayKadham01@gmail.com/{pgId}",1))
		.andExpect(status().isOk()).andExpect(content().string(msg));
		verify(pgService, times(1)).deletePg("akshayKadham01@gmail.com", pg.getPgID());
	}

	}

