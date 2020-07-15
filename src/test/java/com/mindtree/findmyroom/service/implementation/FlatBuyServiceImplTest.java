package com.mindtree.findmyroom.service.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.mindtree.findmyroom.entity.Flat;
import com.mindtree.findmyroom.entity.FlatBuy;
import com.mindtree.findmyroom.entity.PGRent;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.flatdto.FlatDetails;
import com.mindtree.findmyroom.forgotpasswordrepository.ForgotPasswordRepository;
import com.mindtree.findmyroom.repository.FlatBuyRepository;
import com.mindtree.findmyroom.repository.FlatRepository;
import com.mindtree.findmyroom.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlatBuyServiceImplTest {

	@Mock
	FlatBuyRepository fbro;
	
	@Mock
	ForgotPasswordRepository frpo;
	
	@Mock
	UserRepository urpo;

	@Mock
	FlatRepository flro;
	
	@InjectMocks
	FlatBuyServiceImpl fbsi;

	
	@Before
	public void testSetUp() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testpositive() {
		
		FlatDetails flatd = new FlatDetails();
		flatd.setId(1);
		FlatBuy fBuy = new FlatBuy();
		Flat flat = new Flat();
		
		User user = new User();
		user.setUserWallet(0);
		flat.setAvailable(false);
		User owner = new User();
		owner.setUserId(2);
		flat.setUser(user);
		when(flro.getFlatDetails(1)).thenReturn(flat);
		when(flro.save(flat)).thenReturn(null);
		when(frpo.getUser("userEmail")).thenReturn(user);
		when(frpo.getOwner(1)).thenReturn(user);
		fBuy.setCustomer(user);
		fBuy.setPartner(user);
		fBuy.setFlat(flat);
		when(fbro.save(fBuy)).thenReturn(null);
		
	assertEquals(fbsi.buyflat(flatd, "userEmail"), "abc");

	}

	@Test
	public void testnegative() {
		
		FlatDetails flatd = new FlatDetails();
		flatd.setId(1);
		FlatBuy fBuy = new FlatBuy();
		Flat flat = new Flat();
		
		User user = new User();
		
		flat.setAvailable(false);
		User owner = new User();
		owner.setUserId(2);
		flat.setUser(user);
		when(flro.getFlatDetails(1)).thenReturn(flat);
		when(flro.save(flat)).thenReturn(null);
		when(frpo.getUser("userEmail")).thenReturn(user);
		
		when(frpo.getOwner(1)).thenReturn(user);
		fBuy.setCustomer(user);
		fBuy.setPartner(user);
		fBuy.setFlat(flat);
		when(fbro.save(fBuy)).thenReturn(null);
		
	assertNotEquals(fbsi.buyflat(flatd, "userEmail"), "bc");

	}

}
