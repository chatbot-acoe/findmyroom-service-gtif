package com.mindtree.findmyroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.findmyroom.entity.PGRent;

@Repository
public interface PgRentRepository extends JpaRepository<PGRent, Integer>{
	
	@Query("select u from PGRent u where u.customer.userId = :userId")
	public List<PGRent> getdetails(@Param("userId") int userId);
	
	@Query("From PGRent p where p.payinGuest.pgID = :pgid")
	public List<PGRent> getdetailsbtpgId(@Param("pgid")int pgId);
}
