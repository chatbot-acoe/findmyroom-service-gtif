package com.mindtree.findmyroom.service.implementation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindtree.findmyroom.dto.PgDTO;
import com.mindtree.findmyroom.entity.PGRent;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.forgotpasswordrepository.ForgotPasswordRepository;
import com.mindtree.findmyroom.repository.PgRentRepository;
import com.mindtree.findmyroom.repository.PgRepository;
import com.mindtree.findmyroom.repository.UserRepository;

public class PgRentServiceImplTest {

	@Mock
	PgRentRepository pgRentRepository;
	
	@Mock
	ForgotPasswordRepository frpo;
	
	@Mock
	PgRepository pgRepository;
	
	@Mock
	UserRepository urpo;
	
	@InjectMocks
	PgRentServiceImpl pgsi;
	
	@Before
	public void testSetUp() {
		MockitoAnnotations.initMocks(this);

	}
	
	@Test
	public void testpositive() {
		PayingGuest pgt = new PayingGuest();
		User customer = new User();
		User owner = new User();
		PgDTO pgdto = new PgDTO();
		pgdto.setPgID(1);
       PGRent pgRent = new PGRent();
		when(pgRepository.getPgById(1)).thenReturn(pgt);
		when(urpo.getUser("abc")).thenReturn(customer);
		pgt.setUser(owner);
		when(frpo.getOwner(1)).thenReturn(owner);
		pgRent.setCustomer(customer);
		pgRent.setDateOfBooking(LocalDate.now());
		pgRent.setPartner(customer);
		pgRent.setPayinGuest(pgt);
		when(pgRentRepository.save(pgRent)).thenReturn(null);
		assertEquals(pgsi.rentPg(pgdto, "userEmail"),"abc" );
	}

	@Test
	public void testnegative() {
		PayingGuest pgt = new PayingGuest();
		User customer = new User();
		User owner = new User();
		PgDTO pgdto = new PgDTO();
		pgdto.setPgID(1);
       PGRent pgRent = new PGRent();
		when(pgRepository.getPgById(1)).thenReturn(pgt);
		when(urpo.getUser("abc")).thenReturn(customer);
		pgt.setUser(owner);
		when(frpo.getOwner(1)).thenReturn(owner);
		pgRent.setCustomer(customer);
		pgRent.setDateOfBooking(LocalDate.now());
		pgRent.setPartner(customer);
		pgRent.setPayinGuest(pgt);
		when(pgRentRepository.save(pgRent)).thenReturn(null);
		assertNotEquals(pgsi.rentPg(pgdto, "userEmail"),"ab" );
	}

}
