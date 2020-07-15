package com.mindtree.findmyroom.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.findmyroom.dto.PgDTO;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.exception.InvalidUserException;
import com.mindtree.findmyroom.exception.NoSearchResultForPgException;
import com.mindtree.findmyroom.exception.UnabletoDeleteException;
import com.mindtree.findmyroom.service.Interface.PgService;
import com.mindtree.findmyroom.service.implementation.PgServiceImpl;

/**
 * @author M1049123
 *
 */
@RestController
@RequestMapping("/pg")
@CrossOrigin(origins = "*")
public class PgController {

	@Autowired
	PgService pgService = new PgServiceImpl(); // Creating a Bean for PgServiceImpl with "@Autowired" Annotation

	@GetMapping("/pgdetails/{city}/{location}") /*
												 * Request Mapping with two attributes of city and location are received
												 * after mapping to PG
												 */
	public ResponseEntity<Set<PgDTO>> getAllPgDetails(@PathVariable("city") String city,
			@PathVariable("location") String location)
	/*
	 * Assigning the attribute values in URL using "@PathVariable" Annotation and
	 * assigned values to local variables
	 */
	{
		Set<PgDTO> pgDetails = new HashSet<>(); /*
														 * Creating a List of pgDTO with reference pdDetails for storing
														 * List
														 */
		try {
			pgDetails = pgService.retrieveAllPgDetails(city, location.replaceAll("\\s", ""));
			/* Assigning the list of values which are returning from method call */
		} catch (NoSearchResultForPgException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(pgDetails,
				HttpStatus.OK); /*
								 * If the List contains any entities then it returns that list and sends
								 * HttpStatus of OK
								 */
	}

	@GetMapping("/pgdetails/{pgId}") /*
										 * Request Mapping with three attributes of city and location are received after
										 * mapping to PG
										 */
	public ResponseEntity<PgDTO> getPgDetailsbyId(@PathVariable("pgId") int pgId)
	/*
	 * Assigning the attribute in URL using "@PathVariable" Annotation and assigned
	 * values to local variables
	 * 
	 */
	{
		PgDTO pgDetails = new PgDTO(); /*
										 * Creating a List of pgDTO with reference pdDetails for storing List
										 */
		try {
			pgDetails = pgService.retrievePgDetailsbyId(
					pgId); /* Assigning the list of values which are returning from method call */
		} catch (NoSearchResultForPgException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(pgDetails, HttpStatus.OK); /*
																	 * If the List contains any entities then it returns
																	 * that list and sends HttpStatus of OK
																	 */
	}

	// ------------------- Registering a PG -------------------//

	@PostMapping("/register/{ownerEmail}")
	public ResponseEntity<String> registerPg(@PathVariable("ownerEmail") String ownerEmail, @RequestParam String pgname,
			@RequestParam String pgcity, @RequestParam String location, @RequestParam int pgpostalcode,
			@RequestParam String pgdescription, @RequestParam String pgtypeofroom, @RequestParam double pgprice,
			@RequestParam int oneSharingBeds, @RequestParam int twoSharingBeds, @RequestBody MultipartFile file1,
			@RequestBody MultipartFile file2, @RequestBody MultipartFile file3, @RequestBody MultipartFile file4,
			@RequestBody MultipartFile file5) {
		PayingGuest pg = new PayingGuest();
		pg.setPgName(pgname);
		pg.setPgCity(pgcity);
		pg.setLocation(location.replaceAll("\\s", ""));
		pg.setDescription(pgdescription);
		pg.setPgPrice(pgprice);
		pg.setPgPostalCode(pgpostalcode);
		pg.setPgType(pgtypeofroom);
		pg.setOneSharingBeds(oneSharingBeds);
		pg.setTwoSharingBeds(twoSharingBeds);
		try {
			pg.setImage1(file1.getBytes());
			pg.setImage2(file2.getBytes());
			pg.setImage3(file3.getBytes());
			pg.setImage4(file4.getBytes());
			pg.setImage5(file5.getBytes());
			pgService.registerPg(pg, ownerEmail);
		} catch (IOException e) {
			return new ResponseEntity<>("images not uploaded", HttpStatus.METHOD_NOT_ALLOWED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>("Pics not uploaded",HttpStatus.BAD_REQUEST);
		} catch (InvalidUserException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Registration Successfull", HttpStatus.ACCEPTED);
	}

	// ------------------- Updating PG Details ---------------//

	@PutMapping("/update/{ownerEmail}/{pgId}")
	public String updatePg(@PathVariable("ownerEmail") String ownerEmail, @PathVariable("pgId") int PgId,
			@RequestParam String pgname, @RequestParam String pgcity, @RequestParam String location,
			@RequestParam int pgpostalcode, @RequestParam String pgdescription, @RequestParam String pgtypeofroom,
			@RequestParam double pgprice, @RequestParam int oneSharingBeds, @RequestParam int twoSharingBeds,
			@RequestBody MultipartFile file1, @RequestBody MultipartFile file2, @RequestBody MultipartFile file3,
			@RequestBody MultipartFile file4, @RequestBody MultipartFile file5) {
		PayingGuest pg = new PayingGuest();
		pg.setPgName(pgname);
		pg.setPgID(PgId);
		pg.setPgCity(pgcity);
		pg.setLocation(location.replaceAll("\\s", ""));
		pg.setDescription(pgdescription);
		pg.setPgPrice(pgprice);
		pg.setPgPostalCode(pgpostalcode);
		pg.setPgType(pgtypeofroom);
		pg.setOneSharingBeds(oneSharingBeds);
		pg.setTwoSharingBeds(twoSharingBeds);
		try {
			pg.setImage1(file1.getBytes());
			pg.setImage2(file2.getBytes());
			pg.setImage3(file3.getBytes());
			pg.setImage4(file4.getBytes());
			pg.setImage5(file5.getBytes());
			pgService.updatePgofOwner(ownerEmail, PgId,pg);
		} catch (IOException e) {
			return ("images not uploaded");
		} catch (NullPointerException e) {
			return ("Pics not uploaded");
		} catch (InvalidUserException | NoSearchResultForPgException e) {
			return e.getMessage();
		}
		return ("PG Details Updated Successfully");
	}

	// ------------------- Getting PG Details ----------------//

	@GetMapping("/get/{ownerEmail}")
	public ResponseEntity<Set<PgDTO>> getPgsOfOwner(@PathVariable("ownerEmail") String ownerEmail) {
		Set<PgDTO> pgDetails = new HashSet<>();
		try {
			pgDetails = pgService.getPgsofPartner(ownerEmail);
		} catch (NoSearchResultForPgException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (InvalidUserException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(pgDetails, HttpStatus.OK);
	}

	// ----------------- Deleting PG ------------------------//

	@DeleteMapping("/delete/{ownerEmail}/{pgId}")
	public ResponseEntity<String> deletePg(@PathVariable("ownerEmail") String ownerEmail, @PathVariable("pgId") int pgId) {
		try {
			pgService.deletePg(ownerEmail, pgId);
		} catch (NoSearchResultForPgException | InvalidUserException | UnabletoDeleteException e) {
			return new ResponseEntity<>("Unable to delete",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
	}

}