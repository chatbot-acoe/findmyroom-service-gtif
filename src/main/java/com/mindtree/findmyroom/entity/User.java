package com.mindtree.findmyroom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author M1049185
 *
 */

/* User which can be a customer: who uses the web-app for renting PG,Flat or Hotel
 * Partner: who can post his of her properties for sale or rent or book a hotel room
 * */

@Entity
@Table(name = "user")
public class User {
	
	@Id @GeneratedValue
	private int userId;					//Primary key for each user - Unique key which is generated 
	
	private String userFirstName;			//Name of the user
	
	@Column(unique=true)
	private String userEmail;			//Email address of the User - Unique Attribute
	private String userPassword;		//user Password used for login
	private long userPhone;				//Phone number of the user 
	private String userCity;			//City to which user belong to 
	private String userStreet;			//Particular location in the city where user stays 
	private String postalCode;			//Pin code of the user location 
	private String userLastName;		//Last name of the user which is asked for at the time of registration
	private String userType;			//describes whether the user is a customer or owner
	
	@Column(unique=true)
	private String userReferral;		//unique code for each signed up user which he can share to earn points to his wallets
	private double userWallet;			//wallet for each user where the points gets added
	private boolean isVerified;			//Verification for partner/owner
	
	

	public User() {
	
	}
	
	

	public User(int userId, String userFirstName, String userEmail, String userPassword, long userPhone,
			String userCity, String userStreet, String postalCode, String userLastName, String userType,
			String userReferral, double userWallet, boolean isVerified) {

		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userCity = userCity;
		this.userStreet = userStreet;
		this.postalCode = postalCode;
		this.userLastName = userLastName;
		this.userType = userType;
		this.userReferral = userReferral;
		this.userWallet = userWallet;
		this.isVerified = isVerified;
	}



	public User(String userEmail, String userPassword, long userPhone, String userCity,
			String userStreet, String postalCode, String userFirstName, String userLastName, String userType,
			String userReferral, double userWallet) {

		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userCity = userCity;
		this.userStreet = userStreet;
		this.postalCode = postalCode;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userType = userType;
		this.userReferral = userReferral;
		this.userWallet = userWallet;
	}



	public User(int userId, String userEmail, String userPassword, long userPhone, String userCity,
			String userStreet, String postalCode, String userFirstName, String userLastName, String userType,
			String userReferral, double userWallet) {

		this.userId = userId;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userCity = userCity;
		this.userStreet = userStreet;
		this.postalCode = postalCode;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userType = userType;
		this.userReferral = userReferral;
		this.userWallet = userWallet;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserStreet() {
		return userStreet;
	}

	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserReferral() {
		return userReferral;
	}

	public void setUserReferral(String userReferral) {
		this.userReferral = userReferral;
	}

	public double getUserWallet() {
		return userWallet;
	}

	public void setUserWallet(double userWallet) {
		this.userWallet = userWallet;
	}
	
	


	public boolean isVerified() {
		return isVerified;
	}


	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified; 
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userEmail=" + userEmail
				+ ", userPassword=" + userPassword + ", userPhone=" + userPhone + ", userCity=" + userCity
				+ ", userStreet=" + userStreet + ", postalCode=" + postalCode + ", userLastName=" + userLastName
				+ ", userType=" + userType + ", userReferral=" + userReferral + ", userWallet=" + userWallet
				+ ", isVerified=" + isVerified + "]";
	}


	
	
	
	
}
