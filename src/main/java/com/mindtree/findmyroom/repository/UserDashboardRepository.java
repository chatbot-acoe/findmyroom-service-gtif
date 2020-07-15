package com.mindtree.findmyroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mindtree.findmyroom.entity.User;

public interface UserDashboardRepository extends JpaRepository<User,Integer>{
	
	
	
	@Query("SELECT u FROM User u WHERE u.userEmail = :userEmail")//query to fetch details using emailId
	User findUserByUserEmailParam( @Param("userEmail") String userEmail);
	
	
	@Query("SELECT u FROM User u WHERE u.userEmail = :userEmail and u.userPassword = :userPassword")//query to update password
	User findUserByUserEmailAndUserPasswordParams( @Param("userEmail") String userEmail,@Param("userPassword") String userPassword);
	
}
