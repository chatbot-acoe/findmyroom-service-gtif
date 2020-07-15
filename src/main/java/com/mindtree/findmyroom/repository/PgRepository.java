package com.mindtree.findmyroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.entity.User;

/**
 * @author M1049123
 *
 */
@Repository
public interface PgRepository extends JpaRepository<PayingGuest, Integer> {

	@Query(value = "select * from PG p where p.pg_city=:city and p.pg_location=:location and (two_sharing_bed_count>0 or one_sharing_bed_count>0)", nativeQuery = true)
	/*
	 * Creative a native SQL Query for fetching all PGs from required city and
	 * location
	 */
	List<PayingGuest> getAllPg(@Param("city") String city, @Param("location") String location);

	
	
	@Query(value = "select * from PG p where p.pg_city=:city and p.pg_location like :location% and (two_sharing_bed_count>0 or one_sharing_bed_count>0)", nativeQuery = true)
	/*
	 * Creative a native SQL Query for fetching all PGs from required city and if
	 * location has only one letter which matches first letter the Database
	 * locations
	 */
	List<PayingGuest> getAllPgbyFirstLetter(@Param("city") String city, @Param("location") String location);
	
	@Query(value = "select * from PG p where p.pg_id = :id", nativeQuery = true)
	PayingGuest getPgById(@Param("id") int id);


	@Query("From User where userEmail= :email")
	User getUserByEmail(@Param("email") String ownerEmail);


	@Query("From PayingGuest where user= :user")
	List<PayingGuest> getPgsofPartner(@Param("user")User user);

	
}