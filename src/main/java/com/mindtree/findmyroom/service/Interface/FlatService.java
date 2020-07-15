package com.mindtree.findmyroom.service.Interface;

import java.util.List;

import com.mindtree.findmyroom.entity.Flat;
import com.mindtree.findmyroom.flatdto.FlatDetails;
import com.mindtree.findmyroom.flatexceptions.LocationNotFoundException;
import com.mindtree.findmyroom.flatexceptions.NoFlatFound;
import com.mindtree.findmyroom.flatexceptions.UserNotFounException;
import com.mindtree.findmyroom.flatexceptions.addFlatsException;

/**
 * @author M1049287
 *
 */
//Flat service interface
public interface FlatService {

	public List<FlatDetails> getFlatDetails(String flatCity, String flatLocation, String flatPurpose) throws LocationNotFoundException;
	
	public FlatDetails getFlatsById(int id);
	
	public String addFlats(Flat flats,String email) throws addFlatsException;
	
	public List<FlatDetails> getPartnerFlats(String email) throws UserNotFounException;
	
	public String deleteFlat(String email,int id) throws NoFlatFound,UserNotFounException;
	
	public String updateFlat(Flat flats,String email,int id) throws UserNotFounException,NoFlatFound;

}
