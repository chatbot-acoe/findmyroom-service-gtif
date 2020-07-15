package com.mindtree.findmyroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mindtree.findmyroom.entity.Flat;
import com.mindtree.findmyroom.entity.FlatBuy;
import com.mindtree.findmyroom.entity.PGRent;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.entity.User;

public interface AdminDashboardRepository extends JpaRepository<User,Integer> {

	
	@Query("SELECT u FROM User u WHERE u.userType = :userType")//query to fetch all partners
	List<User> findUserByUserType( @Param("userType") String userType);
	
}
