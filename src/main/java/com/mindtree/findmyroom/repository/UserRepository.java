package com.mindtree.findmyroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.findmyroom.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.userEmail = :userEmail")
	public List<User> validEmail(@Param("userEmail") String userEmail);
	
	@Query("select u.userPassword from User u where u.userEmail = :userEmail")
	public String userPassword(@Param("userEmail") String userEmail);

	@Query("select u.userType from User u where u.userEmail = :userEmail")
	public String getRole(@Param("userEmail") String userEmail);
	
	@Query("from User u where u.userEmail = :userEmail")
	public User getUser(@Param("userEmail") String userEmail);
	

	
}
