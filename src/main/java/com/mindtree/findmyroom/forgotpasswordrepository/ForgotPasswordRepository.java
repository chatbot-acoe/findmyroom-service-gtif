package com.mindtree.findmyroom.forgotpasswordrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.findmyroom.entity.User;

@Repository

public interface ForgotPasswordRepository extends JpaRepository<User, Integer>
{
	@Query("select u.userEmail from User u where u.userEmail = :userEmail")
	public String validEmail(@Param("userEmail") String userEmail);
	
	@Query("select u from User u where u.userEmail = :userEmail")
	public User getUser(@Param("userEmail") String userEmail);

	@Query("select u.userPassword from User u where u.userEmail = :userEmail")
	public String userPassword(@Param("userEmail") String userEmail);
	
	@Query("select u from User u where u.userId = :userId")
	public User getOwner(@Param("userId") int userId);
	
}
