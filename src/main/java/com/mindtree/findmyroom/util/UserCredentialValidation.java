package com.mindtree.findmyroom.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class UserCredentialValidation {

	public boolean isFirstNameValid(String firstName) {
		boolean isValid = false;
		if(firstName.length()==0||firstName.length()>50)
		{
			return false;
		}
		Pattern firstNamePattern = Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);
		Matcher firstNameMatcher = firstNamePattern.matcher(firstName);
		if (firstNameMatcher.find()) {
			isValid = true;
		}
		return isValid;
	}

	public boolean isLastNameValid(String lastName) {
		boolean isValid = false;
		if(lastName.isEmpty()) {
			return true;
		}
		else if(lastName.length() > 50) {
			return false;
		}
		Pattern lastNamePattern = Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);
		Matcher lastNameMatcher = lastNamePattern.matcher(lastName);
		if (lastNameMatcher.find()) {
			isValid = true;
		}
		return isValid;
	}

	public boolean isEmailValid(String email) {
		boolean isValid = false;
		if(email.length()==0)
		{
			return false;
		}
		Pattern emailPattern = Pattern.compile("^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z_\\-\\.])+\\.([A-Za-z]{2,3})$",
				Pattern.CASE_INSENSITIVE);
		Matcher emailMatcher = emailPattern.matcher(email);
		if (emailMatcher.find())
			isValid = true;
		return isValid;
	}
	
	public boolean isPasswordValid(String password) {
		boolean isValid = false;
		if(password.length()==0)
		{
			return false;
		}
		Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$",
				Pattern.CASE_INSENSITIVE);
		Matcher passwordMatcher = passwordPattern.matcher(password);
		if (passwordMatcher.find())
			isValid = true;
		return isValid;
	}
	
	public boolean isPhoneValid(long phoneNumber) {
		boolean isValid = false;
		String phone = Long.toString(phoneNumber);
		Pattern phonePattern = Pattern.compile("^(1|2|3|4|5|6|7|8|9)\\d{9}$",
				Pattern.CASE_INSENSITIVE);
		Matcher phoneMatcher = phonePattern.matcher(phone);
		if (phoneMatcher.find())
			isValid = true;
		return isValid;
	}
	
	public boolean isPostalCodeValid(String postalCode) {
		boolean isValid = false;
//		if(postalCode.length() == 0)
//			return false;
		Pattern postalCodePattern = Pattern.compile("^(1|2|3|4|5|6|7|8)\\d{5}$",
				Pattern.CASE_INSENSITIVE);
		Matcher postalCodeMatcher = postalCodePattern.matcher(postalCode);
		if (postalCodeMatcher.find())
			isValid = true;
		return isValid;
	}
	
	public boolean isStreetValid(String street)
	{
		boolean isValid = false;
		Pattern streetPattern = Pattern.compile("^([a-zA-Z0-9]+(\\s)?)*[a-zA-Z]+(\\s)?$",
				Pattern.CASE_INSENSITIVE);
		Matcher streetMatcher = streetPattern.matcher(street);
		if (streetMatcher.find())
			isValid = true;
		return isValid;
	}
	
	public boolean isCityValid(String city) {
		boolean isValid = false;
		Pattern streetPattern = Pattern.compile("^([a-zA-Z]+\\s)*[a-zA-Z]+$",
				Pattern.CASE_INSENSITIVE);
		Matcher streetMatcher = streetPattern.matcher(city);
		if (streetMatcher.find())
			isValid = true;
		return isValid;
	}
	
	public boolean isUserTypeValid(String type) {
		boolean isValid = false;
		if(type.equals("customer") || type.equals("partner")) {
			isValid = true;
		}
		return isValid;
	}

}
