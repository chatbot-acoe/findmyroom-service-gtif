package com.mindtree.findmyroom.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.mindtree.findmyroom.entity.FlatBuy;
import com.mindtree.findmyroom.entity.PGRent;
import com.mindtree.findmyroom.entity.User;

@Repository
public interface FlatBuyRepository extends JpaRepository<FlatBuy, Integer> {
	

	@Query("select u from FlatBuy u where u.customer.userId = :userId")
	public List<FlatBuy> getdetails(@Param("userId") int userId);
}