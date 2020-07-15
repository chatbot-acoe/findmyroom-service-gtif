package com.mindtree.findmyroom.service.implementation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.mindtree.findmyroom.entity.Flat;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.flatexceptions.LocationNotFoundException;
import com.mindtree.findmyroom.flatexceptions.NoFlatFound;
import com.mindtree.findmyroom.flatexceptions.UserNotFounException;
import com.mindtree.findmyroom.repository.FlatRepository;
import com.mindtree.findmyroom.repository.UserRepository;


public class FlatServiceImplTest {

	@Mock
	FlatRepository flatp;
	
	@InjectMocks
	FlatServiceImpl flatService;
	
	@Mock
	UserRepository userRepo;
	
	@Spy
	List<Flat> flat1=new ArrayList<Flat>();
	
	@org.junit.Before
	public void flatdetails() throws Exception
	{
		
		List<Flat> noresult=new ArrayList<Flat>();
		
		Flat fp=new Flat();
		User use=new User();
		use.setUserId(3);
		MockitoAnnotations.initMocks(this);
		flat1=getFlats();
		when(flatp.getAllFlatDetails("bangalore", "jayanagar", "buy")).thenReturn(flat1);
		when(flatp.getFlatDetails(2)).thenReturn(flat1.get(1));
		when(flatp.getAllFlatDetails("bangalore","mangalore","buy")).thenReturn(noresult);
		when(flatp.getFlatDetailsIfLocationIsSingleCharacter("bangalore", "j", "buy")).thenReturn(flat1);
		when(flatp.getFlatDetailsIfLocationIsSingleCharacter("bangalore", "m", "buy")).thenReturn(noresult);
		when(flatp.getAllFlatDetails("bangalore", "jaya", "buy")).thenReturn(flat1);
		when(flatp.save(fp)).thenReturn(fp);
		when(flatp.getPartnerFlats(3)).thenReturn(flat1);
		when(userRepo.getUser("mmm.murali587@gmail.com")).thenReturn(use);
		when(flatp.checkFlatWithEmailId(3,1)).thenReturn(fp);
	
		
	}
	@Test
	public void testGetFlatDetails() {
		
	try {
		assertEquals(flatService.getFlatDetails("bangalore", "jayanagar","buy").size(), 2);
	} catch (LocationNotFoundException e) {
		
	}
	}
	
	
	
	@Test
	public void testGetFlatDetailsIfSingleLetter() {
		
	try {
		assertEquals(flatService.getFlatDetails("bangalore", "j","buy").size(), 2);
	} catch (LocationNotFoundException e) {
		
	}
	}
	
	@Test
	public void testGetFlatDetailsIfSingleLetterNoresult() {
		
	try {
		assertEquals(flatService.getFlatDetails("bangalore", "m","buy").size(), 0);
	} catch (LocationNotFoundException e) {
		
	}
	}
	
	@Test
	public void testGetFlatDetailsHalfOfLocation() {
		
	try {
		assertEquals(flatService.getFlatDetails("bangalore", "jaya","buy").size(), 2);
	} catch (LocationNotFoundException e) {
		
		
	}
	}
	
	
	
	@Test
	public void negativetestGetFlatDetails() {
		
	try {
		assertEquals(flatService.getFlatDetails("bangalore", "mangalore","buy").size(), 0);
	} catch (LocationNotFoundException e) {
		
	}
	}

	@Test
	public void testGetFlatsById() {
		assertEquals(flatService.getFlatsById(2).getFlatName(), "good");
	}	
	
	@Test
	public void testAddFlats() {
		
		Flat p =new Flat();
	
		try
		{
		assertEquals(flatService.addFlats(p,"mmm.murali587@gmail.com"),"Property added successfully");
		}
		catch(Exception e)
		{
			
		}
		
	}
		
	@Test
	public void testgetPartnerFlats()
	{
		
			try {
				assertEquals(flatService.getPartnerFlats("mmm.murali587@gmail.com").size(),2);
				
			} catch (UserNotFounException e) {
				
				
				
			}
}
	
	@Test
	public void negativetestgetPartnerFlats()
	{
		
			try {
				assertEquals(flatService.getPartnerFlats("mmm.murali58790@gmail.com").size(),2);
				
			} catch (UserNotFounException e) {
				
				
				
			}
}
	@Test
	public void testDelete()
	{
		try {
			assertEquals(flatService.deleteFlat("mmm.murali587@gmail.com", 1),"deleted successfully");
		} catch (NoFlatFound | UserNotFounException e) {
			
		}
	}
	
	@Test
	public void negativetestDelete()
	{
		try {
			assertEquals(flatService.deleteFlat("mmm.mur@gmail.com", 1),"deleted successfully");
		} catch (NoFlatFound | UserNotFounException e) {
			
		}
	}
	
	@Test
	public void testupdateFlat()
	{
	
		Flat ff=new Flat();
		try {
			assertEquals(flatService.updateFlat(ff,"mmm.murali587@gmail.com",1),"updated Successfully");
		} catch (UserNotFounException e) {
			
		} catch (NoFlatFound e) {
			
		}
		
	}
	
	@Test
	public void negativetestupdateFlat()
	{
	
		Flat ff=new Flat();
		try {
			assertEquals(flatService.updateFlat(ff,"mmm.mur@gmail.com",1),"updated Successfully");
		} catch (UserNotFounException e) {
			
		} catch (NoFlatFound e) {
			
		}
		
	}
	
	
	public List<Flat> getFlats()
	{
		List<Flat> flat=new ArrayList<Flat>();
		User u=new User();
		u.setUserId(1);
		Flat f=new Flat();
		f.setFlatId(1);
		f.setAvailable(true);
		f.setFaltCategory("buy");
		f.setFaltType("furnished");
		f.setFlatCity("bangalore");
		f.setFlatLocation("jayanagar");
		f.setFlatPrice(20000);
		f.setFlatRooms("2bkh");
		f.setFlatArea("700 square feet");
		f.setFlatDescription("good");
		f.setFlatName("good");
		f.setPic1(null);
		f.setPic2(null);
		f.setPic3(null);
		f.setPic4(null);
		f.setPic5(null);
		f.setUser(u);
		flat.add(f);
		Flat f1=new Flat();
		f1.setFlatId(2);
		f1.setAvailable(true);
		f1.setFaltCategory("buy");
		f1.setFaltType("furnished");
		f1.setFlatCity("bangalore");
		f1.setFlatLocation("jayanagar");
		f1.setFlatPrice(20000);
		f1.setFlatRooms("2bkh");
		f1.setFlatArea("700 square feet");
		f1.setFlatDescription("good");
		f1.setFlatName("good");
		f1.setUser(u);
		f1.setPic1(null);
		f1.setPic2(null);
		f1.setPic3(null);
		f1.setPic4(null);
		f.setPic5(null);
		flat.add(f1);
		return flat;
	}

}
