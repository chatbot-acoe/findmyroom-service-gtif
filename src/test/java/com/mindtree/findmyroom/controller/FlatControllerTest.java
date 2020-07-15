package com.mindtree.findmyroom.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.mindtree.findmyroom.flatdto.FlatDetails;
import com.mindtree.findmyroom.flatexceptions.LocationNotFoundException;
import com.mindtree.findmyroom.service.implementation.FlatServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlatController.class, secure = false)
public class FlatControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FlatServiceImpl flatService;

	@InjectMocks
	private FlatController flatController;

	@Test
	public void testGetFlatDetails() throws Exception {

		mockMvc.perform(get("/Flat/flatdetails/delhi/akbarroad/rent/")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	public void getFlatDetailsException() throws Exception{
		
		when(flatService.getFlatDetails("bangalore", "KRPuram", "rent")).thenThrow(LocationNotFoundException.class);
		assertEquals(new ResponseEntity<List<FlatDetails>>(HttpStatus.NO_CONTENT), flatController.getFlataDetails("bangalore", "KRPuram", "rent"));
	}

	@Before
	public void testSetUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(flatController).build();
	}
  
	@Test
	public void getDetailsById() throws Exception {
		
		FlatDetails flat = new FlatDetails();

		flat.setId(1);
		flat.setFlatName("manyata");

		when(flatService.getFlatsById(1)).thenReturn(flat);

		mockMvc.perform(get("/Flat/flatdetailsById/{id}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	
	
	
	
	@Test
	public void getPartnerFlatsTest() throws Exception {
		FlatDetails flat = new FlatDetails();
		List<FlatDetails> flat1 = new ArrayList<>();
		flat.setId(1);
		flat.setFlatName("manyata");
		flat1.add(flat);

		when(flatService.getPartnerFlats("mmm.murali5@gmail.com")).thenReturn(flat1);
		System.out.println(flat1);
		mockMvc.perform(get("/Flat/partnerFlats/mmm.murali5@gmail.com")).andExpect(status().isNotAcceptable());

	}
	
	

	@Test
	public void deleteFlatTest() throws Exception {
		String msg = "deleted successfully";
		FlatDetails flat = new FlatDetails();
		flat.setId(1);

		when(flatService.getFlatsById(1)).thenReturn(flat);
		when(flatService.deleteFlat("mmm.murali5@gmail.com", 1)).thenReturn(msg);

		mockMvc = MockMvcBuilders.standaloneSetup(flatController).build();
		mockMvc.perform(delete("/Flat/deleteFlat/mmm.murali5@gmail.com/{id}", 1)).andExpect(status().isOk())
				.andExpect(content().string(msg));
		verify(flatService, times(1)).deleteFlat("mmm.murali5@gmail.com", flat.getId());

	}
	
	@Test
	public void deleteFlatException() throws Exception {

			 when(flatService.deleteFlat("akhdaj@gmail.com",999)).thenReturn("no user present with given email");
			 	mockMvc = MockMvcBuilders.standaloneSetup(flatController).build();
				mockMvc.perform(delete("/Flat/deleteFlat/{email}/{id}","akhdaj@gmail.com",999))
				       .andExpect(content().string("no user present with given email"));
				
			
	}

@Test
public void getPartnerDetailsTest() throws Exception
{ when(flatService.getPartnerFlats("akhdaj@gmail.com")).thenReturn(null);
	mockMvc = MockMvcBuilders.standaloneSetup(flatController).build();
    mockMvc.perform(get("/Flat//partnerFlats/{email}","akhdaj@gmail.com"))
    .andExpect(status().isNotAcceptable());
    

	
}


}