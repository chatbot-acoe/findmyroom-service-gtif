package com.mindtree.findmyroom.userdto;

/**
 * 
 * @author M1049185
 *
 */


public class UserDto {				/*A userDto class which is uses only email and password 
									of a user instead of loading all the attributes 
									of the user at the time of login*/
	
	private String userEmail;			//Email used for login
	private String userPassword;		//user password used for login
	public UserDto() {
		
	}
	public UserDto(String userEmail, String userPassword) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	

}
