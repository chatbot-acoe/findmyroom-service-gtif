package com.mindtree.findmyroom.service.implementation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.findmyroom.dto.PgDTO;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.exception.InvalidUserException;
import com.mindtree.findmyroom.exception.NoSearchResultForPgException;
import com.mindtree.findmyroom.exception.UnabletoDeleteException;
import com.mindtree.findmyroom.repository.PgRentRepository;
import com.mindtree.findmyroom.repository.PgRepository;
import com.mindtree.findmyroom.service.Interface.PgService;

/**
 * @author M1049123
 *
 */
@Service
public class PgServiceImpl implements PgService {

	@Autowired
	PgRentRepository pgRent;
	
	@Autowired
	PgRepository pgRepository; // Generating a bean for PGRepository

	// If you want to get all the PGs for particular city and location, this method
	// is called
	public Set<PgDTO> retrieveAllPgDetails(String city, String location) throws NoSearchResultForPgException {
		Set<PgDTO> pgList = new HashSet<>();
		if (location.length() == 1) // Checks for length of location. If locations has only one character it checks
									// for first letter matching
		{
			
			pgRepository.getAllPgbyFirstLetter(city, location).forEach(pg -> {
				PgDTO pgDetails = new PgDTO(); // For Each PG it sets values and pushed into list which to be returned
				pgDetails.setPgID(pg.getPgID());
				pgDetails.setPgName(pg.getPgName());
				pgDetails.setPgCity(pg.getPgCity());
				pgDetails.setPgLocation(pg.getLocation());
				pgDetails.setPgPrice(pg.getPgPrice());
				pgDetails.setPgDescription(pg.getDescription());
				pgDetails.setPgPostalCode(pg.getPgPostalCode());
				pgDetails.setPgType(pg.getPgType());
				pgDetails.setOneSharingBeds(pg.getOneSharingBeds());
				pgDetails.setTwoSharingBeds(pg.getTwoSharingBeds());
				pgDetails.setOwnerName(pg.getUser().getUserFirstName());
				pgDetails.setOwnerPhoneNumber(pg.getUser().getUserPhone());
				pgDetails.setPic1(pg.getImage1());
				pgDetails.setPic2(pg.getImage2());
				pgDetails.setPic3(pg.getImage3());
				pgDetails.setPic4(pg.getImage4());
				pgDetails.setPic5(pg.getImage5());
				pgList.add(pgDetails); // adding each PG details into list of PGs
			});
			if (pgList.isEmpty()) {
				throw new NoSearchResultForPgException("No search result found"); // If list is empty, it throws
																					// exception that no search result
			}
			return pgList; // If location has only one character it returns this list
		}
		pgRepository.getAllPg(city, location).forEach(pg -> { // If location has group of characters it comes here
			PgDTO pgDetails = new PgDTO(); // For Each PG it sets values and pushes into list
			pgDetails.setPgID(pg.getPgID());
			pgDetails.setPgName(pg.getPgName());
			pgDetails.setPgCity(pg.getPgCity());
			pgDetails.setPgLocation(pg.getLocation());
			pgDetails.setPgPrice(pg.getPgPrice());
			pgDetails.setPgDescription(pg.getDescription());
			pgDetails.setPgPostalCode(pg.getPgPostalCode());
			pgDetails.setPgType(pg.getPgType());
			pgDetails.setOneSharingBeds(pg.getOneSharingBeds());
			pgDetails.setTwoSharingBeds(pg.getTwoSharingBeds());
			pgDetails.setOwnerName(pg.getUser().getUserFirstName());
			pgDetails.setOwnerPhoneNumber(pg.getUser().getUserPhone());
			pgDetails.setPic1(pg.getImage1());
			pgDetails.setPic2(pg.getImage2());
			pgDetails.setPic3(pg.getImage3());
			pgDetails.setPic4(pg.getImage4());
			pgDetails.setPic5(pg.getImage5());
			pgList.add(pgDetails); // Adding each PG details into list of PGs
		});
		if (pgList.isEmpty()) {
			throw new NoSearchResultForPgException("No search results found"); // If list is empty, it throws exception
																				// that no search result for that
																				// location or city
		}
		return pgList; // List of PGs are returned
	}

	@Override
	public PgDTO retrievePgDetailsbyId(int pgId) throws NoSearchResultForPgException // If you want to get the PG with
																						// particular PG-Id, then this
															 							// method is called
	{
		PgDTO pgDetails = new PgDTO();
		PayingGuest pg ;
		pg = pgRepository.getPgById(pgId);
		if (pg == null) {
			throw new NoSearchResultForPgException("No search results found");
		}
		pgDetails.setPgID(pg.getPgID());
		pgDetails.setPgName(pg.getPgName());
		pgDetails.setPgCity(pg.getPgCity());
		pgDetails.setPgLocation(pg.getLocation());
		pgDetails.setPgPrice(pg.getPgPrice());
		pgDetails.setPgDescription(pg.getDescription());
		pgDetails.setPgPostalCode(pg.getPgPostalCode());
		pgDetails.setPgType(pg.getPgType());
		pgDetails.setOneSharingBeds(pg.getOneSharingBeds());
		pgDetails.setTwoSharingBeds(pg.getTwoSharingBeds());
		pgDetails.setOwnerName(pg.getUser().getUserFirstName());
		pgDetails.setOwnerPhoneNumber(pg.getUser().getUserPhone());
		pgDetails.setPic1(pg.getImage1());
		pgDetails.setPic2(pg.getImage2());
		pgDetails.setPic3(pg.getImage3());
		pgDetails.setPic4(pg.getImage4());
		pgDetails.setPic5(pg.getImage5());
		return pgDetails; // returns PG details of particular PG-id

	}

	@Override
	public String registerPg(PayingGuest pg, String ownerEmail) throws InvalidUserException {
		pg.setUser(pgRepository.getUserByEmail(ownerEmail));
		if (pg.getUser() == null) {
			throw new InvalidUserException("Email Id not valid");
		}
		try {
			pgRepository.save(pg);
		} catch (Exception e) {
			return "pg not inserted";
		}
		return "pg inserted successfully";

	}

	@Override
	public Set<PgDTO> getPgsofPartner(String ownerEmail) throws NoSearchResultForPgException, InvalidUserException {
		Set<PgDTO> pgList = new HashSet<>();
		User user = pgRepository.getUserByEmail(ownerEmail);
		if (user == null) {
			throw new InvalidUserException("Email Id not present");
		}
		pgRepository.getPgsofPartner(user).forEach(pg -> {
			PgDTO pgDetails = new PgDTO(); // For Each PG it sets values and pushed into list which to be returned
			pgDetails.setPgID(pg.getPgID());
			pgDetails.setPgName(pg.getPgName());
			pgDetails.setPgCity(pg.getPgCity());
			pgDetails.setPgLocation(pg.getLocation());
			pgDetails.setPgPrice(pg.getPgPrice());
			pgDetails.setPgDescription(pg.getDescription());
			pgDetails.setPgPostalCode(pg.getPgPostalCode());
			pgDetails.setPgType(pg.getPgType());
			pgDetails.setOneSharingBeds(pg.getOneSharingBeds());
			pgDetails.setTwoSharingBeds(pg.getTwoSharingBeds());
			pgDetails.setOwnerName(pg.getUser().getUserFirstName());
			pgDetails.setOwnerPhoneNumber(pg.getUser().getUserPhone());
			pgDetails.setPic1(pg.getImage1());
			pgDetails.setPic2(pg.getImage2());
			pgDetails.setPic3(pg.getImage3());
			pgDetails.setPic4(pg.getImage4());
			pgDetails.setPic5(pg.getImage5());
			pgList.add(pgDetails); // adding each PG details into list of PGs
		});
		if (pgList.isEmpty()) {
			throw new NoSearchResultForPgException("No PGs found");
		}
		return pgList;
	}

	@Override
	public String deletePg(String ownerEmail, int pgId) throws InvalidUserException, NoSearchResultForPgException, UnabletoDeleteException {
		User user = pgRepository.getUserByEmail(ownerEmail);
		if (user == null) {
			throw new InvalidUserException("Email Id is incorrect or present in database");
		}
		if (pgRepository.getPgById(pgId) == null) {
			throw new NoSearchResultForPgException("No PGs to delete");
		}
		if(pgRent.getdetailsbtpgId(pgId).isEmpty()) {
			try {
				pgRepository.deleteById(pgId); 
			} catch ( Exception e) {
				throw new UnabletoDeleteException("PG cannot be deleted");
			}
		}
		else {
			throw new UnabletoDeleteException("Cannot delete PG");
		}
		return "PG deleted successfully";
	}

	@Override
	public String updatePgofOwner(String ownerEmail, int pgId, PayingGuest pg1)
			throws NoSearchResultForPgException, InvalidUserException {
		PayingGuest pg = pgRepository.getPgById(pgId);
		if (pg == null) {
			throw new NoSearchResultForPgException("No PGs to update");
		}
		User user = pgRepository.getUserByEmail(ownerEmail);
		if (user == null) {
			throw new InvalidUserException("Email Id is invlaid");
		}
		try {
			pg1.setUser(user);
			pgRepository.save(pg1);
		} catch (Exception e) {
			return "PG Details not updated";
		}
		return "PG details updated successfully";

	}

}