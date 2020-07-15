package com.mindtree.findmyroom.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.findmyroom.entity.Flat;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.flatdto.FlatDetails;
import com.mindtree.findmyroom.flatexceptions.LocationNotFoundException;
import com.mindtree.findmyroom.flatexceptions.NoFlatFound;
import com.mindtree.findmyroom.flatexceptions.UserNotFounException;
import com.mindtree.findmyroom.flatexceptions.addFlatsException;
import com.mindtree.findmyroom.repository.FlatRepository;
import com.mindtree.findmyroom.repository.UserRepository;
import com.mindtree.findmyroom.service.Interface.FlatService;

/**
 * @author M1049287
 *
 */
//Service class to implement the search operation the Flats
@Service
public class FlatServiceImpl implements FlatService {

	@Autowired
	FlatRepository flatRep;


	
	@Autowired
	UserRepository userRepo;

	@Override
	public List<FlatDetails> getFlatDetails(String flatCity, String flatLocation, String flatPurpose) throws LocationNotFoundException
	{

		List<Flat> list = new ArrayList<Flat>();
		List<FlatDetails> flatDetails = new ArrayList<FlatDetails>();
		if (flatLocation.length() == 1) // checks if the location is of lenght one
		{
			list = flatRep.getFlatDetailsIfLocationIsSingleCharacter(flatCity, flatLocation, flatPurpose) ;
																														//if the location is of single character
																										
																											
			if (list.size() != 0) // single
			{																								
				list.forEach(flat -> {
					 
					FlatDetails flatdetails = new FlatDetails();
					flatdetails.setId(flat.getFlatId());
					flatdetails.setFlatName(flat.getFlatName());
					flatdetails.setFlatCity(flat.getFlatCity());
					flatdetails.setFlatLocation(flat.getFlatLocation());
					flatdetails.setFlatPrice(flat.getFlatPrice());
					flatdetails.setFaltType(flat.getFaltType());
					flatdetails.setFlatRooms(flat.getFlatRooms());
					flatdetails.setFlatArea(flat.getFlatArea());
					flatdetails.setFlatDescription(flat.getFlatDescription());
					flatdetails.setFlatPostalCode(flat.getFlatPostalCode());
					flatdetails.setOwnerName(flat.getUser().getUserFirstName());
					flatdetails.setOwnerPhone(flat.getUser().getUserPhone());
					flatdetails.setPic1(flat.getPic1());
					flatdetails.setPic2(flat.getPic2());
					flatdetails.setPic3(flat.getPic3());
					flatdetails.setPic4(flat.getPic4());
					flatdetails.setPic5(flat.getPic5());
					flatDetails.add(flatdetails);
				});

				return flatDetails;
			}
			else
			{
				throw new LocationNotFoundException("Location not found");
			}
	
			

			
		} else // check the location in the database
		{
			list = flatRep.getAllFlatDetails(flatCity, flatLocation, flatPurpose); // It fetch all the Flats with given
																					// location
			if (list.size() == 0) {
				list = flatRep.getAllFlatDetailsMatchesHalfString(flatCity, flatLocation.substring(0, flatLocation.length()/2), flatPurpose); // Get Result  form database 
					
				if(list.size()!=0)
				{
				list.forEach(flat -> {

					FlatDetails flatdetails = new FlatDetails();
					flatdetails.setId(flat.getFlatId());
					flatdetails.setFlatName(flat.getFlatName());
					flatdetails.setFlatCity(flat.getFlatCity());
					flatdetails.setFlatLocation(flat.getFlatLocation());
					flatdetails.setFlatPrice(flat.getFlatPrice());
					flatdetails.setFaltType(flat.getFaltType());
					flatdetails.setFlatDescription(flat.getFlatDescription());
					flatdetails.setFlatRooms(flat.getFlatRooms());
					flatdetails.setFlatArea(flat.getFlatArea());
					flatdetails.setFlatPostalCode(flat.getFlatPostalCode());
					
					flatdetails.setOwnerName(flat.getUser().getUserFirstName());
					flatdetails.setOwnerPhone(flat.getUser().getUserPhone());
					flatdetails.setPic1(flat.getPic1());
					flatdetails.setPic2(flat.getPic2());
					flatdetails.setPic3(flat.getPic3());
					flatdetails.setPic4(flat.getPic4());
					flatdetails.setPic5(flat.getPic5());
					flatDetails.add(flatdetails);
				}); 
				
				
				return flatDetails;
				}
				else
				{
					throw new LocationNotFoundException("Sorry no result Found");
				}
			
			} else {
				
				list.forEach(flat -> {

					FlatDetails flatdetails = new FlatDetails();
					flatdetails.setId(flat.getFlatId());
					flatdetails.setFlatName(flat.getFlatName());
					flatdetails.setFlatCity(flat.getFlatCity());
					flatdetails.setFlatLocation(flat.getFlatLocation());
					flatdetails.setFlatPrice(flat.getFlatPrice());
					flatdetails.setFaltType(flat.getFaltType());
					flatdetails.setFlatDescription(flat.getFlatDescription());
					flatdetails.setFlatRooms(flat.getFlatRooms());
					flatdetails.setFlatArea(flat.getFlatArea());
					flatdetails.setFlatPostalCode(flat.getFlatPostalCode());
				
					flatdetails.setOwnerName(flat.getUser().getUserFirstName());
					flatdetails.setOwnerPhone(flat.getUser().getUserPhone());
					flatdetails.setPic1(flat.getPic1());
					flatdetails.setPic2(flat.getPic2());
					flatdetails.setPic3(flat.getPic3());
					flatdetails.setPic4(flat.getPic4());
					flatdetails.setPic5(flat.getPic5());
					flatDetails.add(flatdetails);
				}); 
				return flatDetails;
			}
		}

	}
	
	@Override
	public FlatDetails getFlatsById(int id) {
		
		Flat flat = new Flat();
		flat = flatRep.getFlatDetails(id);
		FlatDetails flatdetails = new FlatDetails();
		flatdetails.setId(flat.getFlatId());
		flatdetails.setFlatName(flat.getFlatName());
		flatdetails.setFlatCity(flat.getFlatCity());
		flatdetails.setFlatLocation(flat.getFlatLocation());
		flatdetails.setFlatPrice(flat.getFlatPrice());
		flatdetails.setFaltType(flat.getFaltType());
		flatdetails.setFlatDescription(flat.getFlatDescription());
		flatdetails.setFlatRooms(flat.getFlatRooms());
		flatdetails.setFlatArea(flat.getFlatArea());
		flatdetails.setFlatPostalCode(flat.getFlatPostalCode());
		
		flatdetails.setOwnerName(flat.getUser().getUserFirstName());
		flatdetails.setOwnerPhone(flat.getUser().getUserPhone());
		flatdetails.setPic1(flat.getPic1());
		flatdetails.setPic2(flat.getPic2());
		flatdetails.setPic3(flat.getPic3());
		flatdetails.setPic4(flat.getPic4());
		flatdetails.setPic5(flat.getPic5());
		
	
	
		return flatdetails;
		
	}

	@Override
	public String addFlats(Flat flats,String email) throws addFlatsException {
	
		try
		{
		User user=userRepo.getUser(email);
		flats.setUser(user);
		flatRep.save(flats);
		return "Property added successfully";
		}
		catch (Exception e) {
			throw new addFlatsException("Unable to add Property");
		}
	}

	@Override
	public List<FlatDetails> getPartnerFlats(String email) throws UserNotFounException {
		
		try
		{
			User user=userRepo.getUser(email);
		List<Flat> list=flatRep.getPartnerFlats(user.getUserId());
		List<FlatDetails> flatDetails = new ArrayList<FlatDetails>();
		
		list.forEach(flat -> {

			FlatDetails flatdetails = new FlatDetails();
			flatdetails.setId(flat.getFlatId());
			flatdetails.setFlatName(flat.getFlatName());
			flatdetails.setFlatCity(flat.getFlatCity());
			flatdetails.setFlatLocation(flat.getFlatLocation());
			flatdetails.setFlatPrice(flat.getFlatPrice());
			flatdetails.setFaltType(flat.getFaltType());
			flatdetails.setFlatDescription(flat.getFlatDescription());
			flatdetails.setFlatRooms(flat.getFlatRooms());
			flatdetails.setFlatArea(flat.getFlatArea());
			flatdetails.setFlatPostalCode(flat.getFlatPostalCode());
		
			flatdetails.setOwnerName(flat.getUser().getUserFirstName());
			flatdetails.setOwnerPhone(flat.getUser().getUserPhone());
			flatdetails.setPic1(flat.getPic1());
			flatdetails.setPic2(flat.getPic2());
			flatdetails.setPic3(flat.getPic3());
			flatdetails.setPic4(flat.getPic4());
			flatdetails.setPic5(flat.getPic5());
			flatDetails.add(flatdetails);
		}); 
		return flatDetails;
		}
		catch (Exception e) {
			throw new UserNotFounException("no userPresent with given email");
		}
	}

	@Override
	public String deleteFlat(String email,int id) throws NoFlatFound,UserNotFounException {
		
		try
		{
		User user=userRepo.getUser(email);
		Flat flatToDelete= flatRep.checkFlatWithEmailId(user.getUserId(),id);
		if(flatToDelete==null)
		{
			throw new NoFlatFound("No flat Present to delete");
		}
		else
		{ 
			flatRep.deleteById(id);
			return "deleted successfully";
		}
		}
		catch (Exception e) {
			throw new UserNotFounException("no user present with given email");
		}
		
		 
	}

	@Override
	public String updateFlat(Flat flats, String email, int id) throws UserNotFounException, NoFlatFound {
		try
		{
		User user=userRepo.getUser(email);
		Flat flatToDelete= flatRep.checkFlatWithEmailId(user.getUserId(),id);
		if(flatToDelete==null)
		{
			throw new NoFlatFound("No flat Present to delete");
		}
		else
		{
			System.out.println(id);
			flats.setFlatId(id);
			flats.setUser(flatToDelete.getUser());
			flatRep.save(flats);
			return "updated Successfully";
		}
		}
		catch (Exception e) {
			throw new UserNotFounException("no user present with given email");
		}
		
	}

}
