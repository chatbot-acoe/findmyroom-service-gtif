package com.mindtree.findmyroom.service.Interface;

import java.util.Set;

import com.mindtree.findmyroom.dto.PgDTO;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.exception.InvalidUserException;
import com.mindtree.findmyroom.exception.NoSearchResultForPgException;
import com.mindtree.findmyroom.exception.UnabletoDeleteException;

/**
 * @author M1049123
 *
 */
public interface PgService {

	public Set<PgDTO> retrieveAllPgDetails(String city, String location) throws NoSearchResultForPgException;

	public PgDTO retrievePgDetailsbyId(int pgId) throws NoSearchResultForPgException;

	public String registerPg(PayingGuest pg, String ownerEmail) throws InvalidUserException;

	public Set<PgDTO> getPgsofPartner(String ownerEmail) throws NoSearchResultForPgException, InvalidUserException;

	public String deletePg(String ownerEmail, int pgId)
			throws InvalidUserException, NoSearchResultForPgException, UnabletoDeleteException;

	public String updatePgofOwner(String ownerEmail, int pgId, PayingGuest pg1)
			throws NoSearchResultForPgException, InvalidUserException;
}
