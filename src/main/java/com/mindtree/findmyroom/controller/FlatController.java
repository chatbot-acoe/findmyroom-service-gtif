package com.mindtree.findmyroom.controller;

import java.io.IOException;
import java.util.List;

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

import com.mindtree.findmyroom.entity.Flat;
import com.mindtree.findmyroom.flatdto.FlatDetails;
import com.mindtree.findmyroom.flatexceptions.LocationNotFoundException;
import com.mindtree.findmyroom.flatexceptions.NoFlatFound;
import com.mindtree.findmyroom.flatexceptions.UserNotFounException;
import com.mindtree.findmyroom.flatexceptions.addFlatsException;
import com.mindtree.findmyroom.service.implementation.FlatServiceImpl;

/**
 * @author M1049287
 *
 */
@RestController
@RequestMapping("/Flat")
@CrossOrigin(origins = "*")
public class FlatController {

	@Autowired
	FlatServiceImpl flatService; // Flat service class object

	@GetMapping("/flatdetails/{flatCity}/{flatLocation}/{flatPurpose}") // Controller to Query the Database for flats
																		// based on location,city,flatpurpose(rent,buy)
	public ResponseEntity<List<FlatDetails>> getFlataDetails(@PathVariable("flatCity") String flatCity,
			@PathVariable("flatLocation") String flatLocation, @PathVariable("flatPurpose") String flatPurpose) {
		
		try {
			return new ResponseEntity<List<FlatDetails>>(
					flatService.getFlatDetails(flatCity, flatLocation.replaceAll("\\s", ""), flatPurpose),
					HttpStatus.OK);
		} catch (LocationNotFoundException e) {
			
			return new ResponseEntity<List<FlatDetails>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/flatdetailsById/{id}")
	public ResponseEntity<FlatDetails> getFlatsById(@PathVariable("id") int id) {
		return new ResponseEntity<FlatDetails>(flatService.getFlatsById(id), HttpStatus.OK);

	}

	@PostMapping("/addflat/{useremail}")
	public ResponseEntity<String> uploadImage(@PathVariable("useremail") String email, @RequestParam String flatName,
			@RequestParam String flatCity, @RequestParam String flatLocation, @RequestParam double flatPrice,
			@RequestParam String faltType, @RequestParam String faltCategory, @RequestParam String flatRooms,
			@RequestParam boolean isAvailable, @RequestParam int flatPostalCode, @RequestParam String flatArea,
			@RequestParam String flatDescription, @RequestBody MultipartFile file1, @RequestBody MultipartFile file2,
			@RequestBody MultipartFile file3, @RequestBody MultipartFile file4, @RequestBody MultipartFile file5) {
		

		Flat flats;
		try {
			flats = new Flat(flatName, flatCity, flatLocation.replaceAll("\\s", ""), flatPrice, faltType, faltCategory, flatRooms,
					isAvailable, flatPostalCode, flatArea, flatDescription, file1.getBytes(), file2.getBytes(),
					file3.getBytes(), file4.getBytes(), file5.getBytes());
			try {
				return new ResponseEntity<String>(flatService.addFlats(flats, email), HttpStatus.OK);
			} catch (addFlatsException e) {
				
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (IOException e) {
		
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value="/partnerFlats/{email}")
	public ResponseEntity<List<FlatDetails>> getPartnerFlats(@PathVariable("email") String email){
		try {
			return new ResponseEntity<List<FlatDetails>>(flatService.getPartnerFlats(email),HttpStatus.OK);
		} catch (UserNotFounException e) {
			
			return new ResponseEntity<List<FlatDetails>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@DeleteMapping(value="/deleteFlat/{email}/{id}")
	public ResponseEntity<String> deleteFlat(@PathVariable("email") String email,@PathVariable("id") int id)
	{
		
		try {
			return new ResponseEntity<String>(flatService.deleteFlat(email,id),HttpStatus.OK);
		} catch (NoFlatFound | UserNotFounException e) {
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NO_CONTENT);
		}
		
	} 
	 
	@PutMapping("/updateFlat/{useremail}/{id}")
	public ResponseEntity<String> updateProperty(@PathVariable("useremail") String email,@PathVariable("id") int id, @RequestParam String flatName,
			@RequestParam String flatCity, @RequestParam String flatLocation, @RequestParam double flatPrice,
			@RequestParam String faltType, @RequestParam String faltCategory, @RequestParam String flatRooms,
			@RequestParam boolean isAvailable, @RequestParam int flatPostalCode, @RequestParam String flatArea,
			@RequestParam String flatDescription, @RequestBody MultipartFile file1, @RequestBody MultipartFile file2,
			@RequestBody MultipartFile file3, @RequestBody MultipartFile file4, @RequestBody MultipartFile file5)
	{

		Flat flats;
		try {
			flats = new Flat(flatName, flatCity, flatLocation.replaceAll("\\s", ""), flatPrice, faltType, faltCategory, flatRooms,
					isAvailable, flatPostalCode, flatArea, flatDescription, file1.getBytes(), file2.getBytes(),
					file3.getBytes(), file4.getBytes(), file5.getBytes());
				
			
				try {
					return new ResponseEntity<String>(flatService.updateFlat(flats, email,id), HttpStatus.OK);
				} catch (UserNotFounException e) {
					
					return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
				} catch (NoFlatFound e) {
					
					return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
				}
			
		} catch (IOException e) {
			
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		
	}

}
