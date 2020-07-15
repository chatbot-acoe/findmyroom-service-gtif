package com.mindtree.findmyroom.service.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import com.mindtree.findmyroom.entity.PGRent;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.exception.InvalidUserException;
import com.mindtree.findmyroom.exception.NoSearchResultForPgException;
import com.mindtree.findmyroom.exception.UnabletoDeleteException;
import com.mindtree.findmyroom.repository.PgRentRepository;
import com.mindtree.findmyroom.repository.PgRepository;

public class PgServiceImplTest {

	@Mock
	PgRepository pgRepository;
	
	@Mock
	PgRentRepository pgRent;

	@InjectMocks
	private PgServiceImpl pgService;

	@Spy
	private List<PayingGuest> pgList = new ArrayList<PayingGuest>();

	@Before
	public void testSetUp() {
		User user = new User(1, "affSDA", "fdsafasf@gamil.com", 1123456387, "dafs", "Fsdafa", "asfdfs", "Mani", "dafas", "adfa",
				"dzcfsd", 0.00);
		PayingGuest pg = new PayingGuest();
		MockitoAnnotations.initMocks(this);
		pgList = getPgs();
		List<PGRent> pgrent=new ArrayList<>();
		when(pgRepository.getAllPg("bangalore", "whitefield")).thenReturn(pgList);
		when(pgRepository.getAllPgbyFirstLetter("bangalore", "w")).thenReturn(pgList);
		when(pgRepository.getPgById(1)).thenReturn(pgList.get(0));
		when(pgRepository.getUserByEmail("fdsafasf@gamil.com")).thenReturn(user);
		when(pgRepository.save(pg)).thenReturn(pg);
		when(pgRepository.getPgsofPartner(user)).thenReturn(pgList);
		when(pgRepository.getPgById(1)).thenReturn(pgList.get(0));
		when(pgRent.getdetailsbtpgId(1)).thenReturn(pgrent);
	}

	@Test
	public void testRetrieveAllPgDetailsPositive() {
		try {
			assertEquals(pgService.retrieveAllPgDetails("bangalore", "whitefield").size(), 3);
			assertEquals(pgService.retrieveAllPgDetails("bangalore", "w").size(), 3);
		} catch (NoSearchResultForPgException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void testRetrieveAllPgDetailsNegative(){
		
		try {
			assertNotEquals(pgService.retrieveAllPgDetails("bangalore", "fhhgcgj").size(),0);
			assertNotEquals(pgService.retrieveAllPgDetails("bangalore", "n").size(), 0);
			assertNotEquals(pgService.retrieveAllPgDetails("delhi", "mockito").size(), 0);
		} catch (NoSearchResultForPgException e) {
			System.out.println(e.getMessage());
		} 
		
	}

	@Test
	public void testRetrievePgDetailsbyIdPositive(){
		try {
			assertEquals(pgService.retrievePgDetailsbyId(1).getPgID(), 1);
		} catch (NoSearchResultForPgException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testRetrievePgDetailsbyIdNegative() {
		try {
			assertNotEquals(pgService.retrievePgDetailsbyId(3).getPgID(), 0);
		} catch (NoSearchResultForPgException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testRegisterPgPostive()
	{
		PayingGuest pg = new PayingGuest();
		try {
			assertEquals(pgService.registerPg(pg, "fdsafasf@gamil.com"),"pg inserted successfully" );
		} catch (InvalidUserException e) { 
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testRegisterPgNegative()
	{
		PayingGuest pg = new PayingGuest();
		try {
			assertNotEquals(pgService.registerPg(pg, "jsdjjsad"), "pg not inserted");
		} catch (InvalidUserException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testUpdatePgPostive()
	{
		PayingGuest pg = new PayingGuest();
		try {
			assertEquals(pgService.updatePgofOwner("fdsafasf@gamil.com", 1, pg), "PG details updated successfully");
		} catch (NoSearchResultForPgException | InvalidUserException e) {
			System.out.println(e.getMessage()); 
		}
	}
	
	@Test
	public void testUpdatePgNegative()
	{
		PayingGuest pg = new PayingGuest();
		try {
			assertNotEquals(pgService.updatePgofOwner("jsdjjsad", 1, pg), "PG details updated successfully");
		} catch (NoSearchResultForPgException | InvalidUserException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testgetPgsofPartnerPositive()
	{
		try {
			assertEquals(pgService.getPgsofPartner("fdsafasf@gamil.com").size(), 3);
		} catch (NoSearchResultForPgException | InvalidUserException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testgetPgsofPartnerNegative()
	{
		try {
			assertNotEquals(pgService.getPgsofPartner("fdsafasf").size(), 0);
		} catch (NoSearchResultForPgException | InvalidUserException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testdeletePgPostive()
	{
		try {
			assertEquals(pgService.deletePg("fdsafasf@gamil.com", 1), "PG deleted successfully");
		} catch (NoSearchResultForPgException | UnabletoDeleteException | InvalidUserException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testdeletePgNegative()
	{
		try {
			assertNotEquals(pgService.deletePg("fdsafasf@gamil.com", 0), "not deletes");
		} catch (NoSearchResultForPgException | UnabletoDeleteException | InvalidUserException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<PayingGuest> getPgs() {
		List<PayingGuest> pgs = new ArrayList<PayingGuest>();
		PayingGuest pg1 = new PayingGuest();
		pg1.setPgID(1);
		pg1.setPgName("Manikanta PG for women");
		pg1.setPgCity("bangalore");
		pg1.setLocation("whitefield");
		pg1.setDescription("Good Facilities");
		pg1.setPgPrice(7500.00);
		pg1.setPgPostalCode(522222);
		pg1.setPgType("1Sharing");
		pg1.setOneSharingBeds(20);
		pg1.setTwoSharingBeds(0);
		
		pg1.setUser(new User(1, "affSDA", "fdsafasf@gamil.com", 1123456387, "dafs", "Fsdafa", "asfdfs", "Mani", "dafas", "adfa",
				"dzcfsd", 0.00));
		pgs.add(pg1);
		pg1 = new PayingGuest();
		pg1.setPgID(2);
		pg1.setPgName("Manikanta PG for women");
		pg1.setPgCity("bangalore");
		pg1.setLocation("whitefield");
		pg1.setDescription("Good Facilities");
		pg1.setPgPrice(7500.00);
		pg1.setPgPostalCode(522222);
		pg1.setPgType("1Sharing");
		pg1.setOneSharingBeds(20);
		pg1.setTwoSharingBeds(0);
		pg1.setUser(new User(2, "ads", "afsd@gmail.com", 1212345235, "fasd", "Dafasd", "dsafas", "Mani", "daf", "dafs", "asfdd",
				0.00));
		pgs.add(pg1);
		pg1 = new PayingGuest();
		pg1.setPgID(3);
		pg1.setPgName("Manikanta PG for women");
		pg1.setPgCity("bangalore");
		pg1.setLocation("whitefield");
		pg1.setDescription("Good Facilities");
		pg1.setPgPrice(7500.00);
		pg1.setPgPostalCode(522222);
		pg1.setPgType("1Sharing");
		pg1.setOneSharingBeds(20);
		pg1.setTwoSharingBeds(0);
		pg1.setUser(new User(2, "ads", "afsd@gmail.com", 1212345235, "fasd", "Dafasd", "dsafas", "Mani", "daf", "dafs", "asfdd",
				0.00));
		pgs.add(pg1);
		return pgs;
	}

}
