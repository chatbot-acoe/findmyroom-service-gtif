package com.mindtree.findmyroom.service.implementation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindtree.findmyroom.entity.Flat;
import com.mindtree.findmyroom.entity.FlatBuy;
import com.mindtree.findmyroom.entity.FlatBuyDto;
import com.mindtree.findmyroom.entity.PGRent;
import com.mindtree.findmyroom.entity.PGRentDto;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.exception.InvalidEmailException;
import com.mindtree.findmyroom.exception.UserExistsException;
import com.mindtree.findmyroom.forgotpasswordrepository.ForgotPasswordRepository;
import com.mindtree.findmyroom.repository.FlatBuyRepository;
import com.mindtree.findmyroom.repository.PgRentRepository;
import com.mindtree.findmyroom.repository.UserRepository;

public class SendRefferedEmailImplTest {

	
	@Mock
	ForgotPasswordRepository frpo;
	
	@Mock
	UserRepository urpo;
	
	@Mock
	PgRentRepository pgRentRepository;
	
	@Mock
	FlatBuyRepository fbro;
	
	@InjectMocks
	SendRefferedEmailImpl srei;
	
	@Before
	public void testSetUp() {
		MockitoAnnotations.initMocks(this);

	}
	
	@Test
	public void test() {
		User user = new User();
		user.setUserId(1);
		User partner = new User();
		Flat flat = new Flat(); 
		flat.setFlatId(1);
		FlatBuy fbu = new FlatBuy();
		fbu.setFlatBuyId(1);
		fbu.setDateOfBooking(LocalDate.now());
		fbu.setType("a");
		fbu.setCustomer(user);
		fbu.setFlat(flat);
		fbu.setPartner(partner);
		List<FlatBuy> flatbuylist = new ArrayList<>();
		flatbuylist.add(fbu);
		
		when(fbro.getdetails(1)).thenReturn(flatbuylist);
		List<FlatBuyDto> fdt = new ArrayList<>();
		for(FlatBuy f : flatbuylist)
		{
			FlatBuyDto fbdto = new FlatBuyDto();
			fbdto.setBuyedDate(f.getDateOfBooking());
			fbdto.setFlatName(f.getFlat().getFlatName());
			fbdto.setOwnerName(f.getPartner().getUserFirstName());
			fbdto.setPrice(f.getFlat().getFlatPrice());
			fdt.add(fbdto);
		}
		
		assertEquals(srei.getbookindetailsf(1).size(), 1);
		
		User user1 = new User();
		user1.setUserId(1);
		User partner1 = new User();
		PayingGuest pgs = new PayingGuest();
		PGRent pgre = new PGRent();
		pgre.setPgRentId(1);
		pgre.setCustomer(user1);
		pgre.setDateOfBooking(LocalDate.now());
		pgre.setPartner(partner1);
		pgre.setPayinGuest(pgs);
		List<PGRent> pgrentlist = new ArrayList<>();
		pgrentlist.add(pgre);
		when(pgRentRepository.getdetails(1)).thenReturn(pgrentlist);
		PGRentDto pgdo = new PGRentDto();
		List<PGRentDto> pgdto  = new ArrayList<>();
		for(PGRent p : pgrentlist)
		{
			pgdo.setBuyedDate(p.getDateOfBooking());
			pgdo.setOwnerName(p.getPartner().getUserFirstName());
			pgdo.setPgName(p.getPayinGuest().getPgName());
			pgdo.setPrice(p.getPayinGuest().getPgPrice());
			pgdto.add(pgdo);
		}
		assertEquals(srei.getbookindetailsp(1).size(), 1);
	}
	@Test
	public void testnegative() {
		User user = new User();
		user.setUserId(1);
		User partner = new User();
		Flat flat = new Flat(); 
		flat.setFlatId(1);
		FlatBuy fbu = new FlatBuy();
		fbu.setFlatBuyId(1);
		fbu.setDateOfBooking(LocalDate.now());
		fbu.setType("a");
		fbu.setCustomer(user);
		fbu.setFlat(flat);
		fbu.setPartner(partner);
		List<FlatBuy> flatbuylist = new ArrayList<>();
		flatbuylist.add(fbu);
		
		when(fbro.getdetails(1)).thenReturn(flatbuylist);
		List<FlatBuyDto> fdt = new ArrayList<>();
		for(FlatBuy f : flatbuylist)
		{
			FlatBuyDto fbdto = new FlatBuyDto();
			fbdto.setBuyedDate(f.getDateOfBooking());
			fbdto.setFlatName(f.getFlat().getFlatName());
			fbdto.setOwnerName(f.getPartner().getUserFirstName());
			fbdto.setPrice(f.getFlat().getFlatPrice());
			fdt.add(fbdto);
		}
		
		assertNotEquals(srei.getbookindetailsf(1).size(), 3);
		
		User user1 = new User();
		user1.setUserId(1);
		User partner1 = new User();
		PayingGuest pgs = new PayingGuest();
		PGRent pgre = new PGRent();
		pgre.setPgRentId(1);
		pgre.setCustomer(user1);
		pgre.setDateOfBooking(LocalDate.now());
		pgre.setPartner(partner1);
		pgre.setPayinGuest(pgs);
		List<PGRent> pgrentlist = new ArrayList<>();
		pgrentlist.add(pgre);
		when(pgRentRepository.getdetails(1)).thenReturn(pgrentlist);
		PGRentDto pgdo = new PGRentDto();
		List<PGRentDto> pgdto  = new ArrayList<>();
		for(PGRent p : pgrentlist)
		{
			pgdo.setBuyedDate(p.getDateOfBooking());
			pgdo.setOwnerName(p.getPartner().getUserFirstName());
			pgdo.setPgName(p.getPayinGuest().getPgName());
			pgdo.setPrice(p.getPayinGuest().getPgPrice());
			pgdto.add(pgdo);
		}
		assertNotEquals(srei.getbookindetailsp(1).size(), 3);
	}
	

	@Test
	public void sendmailTest() 
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
		when(urpo.getUser(user.getUserEmail())).thenReturn(user);
		when( urpo.getUser(user.getUserEmail())).thenReturn(user);
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("findmyroom07@gmail.com", "gandhi@JI"));
		email.setStartTLSEnabled(true);
		try {
			email.setFrom("findmyroom07@gmail.com");
		} catch (EmailException e) {
			
		}
		email.setSubject("Hello Sir/Madam , greetings from FindMyRoom");
		try {
			email.setMsg("Hello Sir/Madam , you have been reffered from the user\t" +user.getUserFirstName() + "\twith emailid\t" +user.getUserEmail() + "\tkindly use the code\t" +user.getUserReferral() + "\t to get exciting offers in our website\nSignUp now\n" +"Thankyou");
		} catch (EmailException e) {
		}
		try {
			email.addTo(user.getUserEmail());
		} catch (EmailException e) {
			
		}
		try {
			assertEquals(srei.sendmail(user.getUserFirstName(), user.getUserEmail(), user.getUserReferral(), user.getUserEmail()),"emailsent");
		} catch (UserExistsException | InvalidEmailException e) {
			
		}
	}
	
	
}
