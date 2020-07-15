package com.mindtree.findmyroom.service.implementation;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.exception.InvalidEmailException;
import com.mindtree.findmyroom.exception.InvalidUserCredentialsException;
import com.mindtree.findmyroom.exception.PasswordInvalidException;
import com.mindtree.findmyroom.exception.UserExistsException;
import com.mindtree.findmyroom.repository.UserRepository;
import com.mindtree.findmyroom.service.Interface.UserService;
import com.mindtree.findmyroom.userdto.UserDto;
import com.mindtree.findmyroom.util.UserCredentialValidation;

/**
 * 
 * @author M1049185
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserCredentialValidation userValidation;

	@Override
	public String registerUser(User user) throws UserExistsException, InvalidUserCredentialsException {
//		UserCredentialValidation userValidation=new UserCredentialValidation();
		if (userValidation.isFirstNameValid(user.getUserFirstName())
				&& userValidation.isLastNameValid(user.getUserLastName())
				&& userValidation.isEmailValid(user.getUserEmail())
				&& userValidation.isPasswordValid(user.getUserPassword())
				&& userValidation.isPhoneValid(user.getUserPhone())
				&& userValidation.isCityValid(user.getUserCity())
				&& userValidation.isPostalCodeValid(user.getPostalCode())
				&& userValidation.isStreetValid(user.getUserStreet())
				&& userValidation.isUserTypeValid(user.getUserType())) {
			String userEmail = user.getUserEmail();
			user.setUserEmail(userEmail.toLowerCase()); // converting the email to lowercase to avoid conflicts
														// while fetching data of a particular user
			List<User> userList = userRepo.validEmail(user.getUserEmail()); // checking whether the entered
																			// email-id is already registered or
																			// not
			if (!(userList.isEmpty()))
				throw new UserExistsException("Registered User"); // If registered throw exception saying
																	// registered user

			user.setUserReferral(createReferral()); // If user is new, then generate a referral code of length 6
													// to the user before saving it to database
			userRepo.save(user);
			return "Registered Successfully"; // On successful registration return Registered Successfully
		}
		else
		{
			throw new InvalidUserCredentialsException("UserCredentials are Invalid");
		}
	}

	@Override
	public String authenticateUser(UserDto user) throws PasswordInvalidException, InvalidEmailException {
		String userEmail = user.getUserEmail();
		user.setUserEmail(userEmail.toLowerCase());
		System.out.println(user.getUserEmail() + user.getUserPassword());
		List<User> userList = userRepo.validEmail(user.getUserEmail()); // For Login - check E-mail entered is
																		// registered Email or not
		if (userList.isEmpty())
			throw new InvalidEmailException("Invalid User Name"); // If Email-id is not registered, throw error
		else {
			String userPassword = userRepo.userPassword(user.getUserEmail()); // For the registered Email get password
																				// and check if its matching to the
			System.out.println(userPassword);																	// password entered
			System.out.println(user.getUserPassword());
			if (userPassword.equals(user.getUserPassword()))
				return userRepo.getRole(user.getUserEmail()); // If user is authenticated send the role of that user to
																// controller
			else
				throw new PasswordInvalidException("Invalid User Name or Password"); // If user name and password
																						// mismatch, then throw error
		}

	}

	public String createReferral() {
		int codeLength = 6; // length of the referral code
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray(); // referral code made up of these
																				// characters
		StringBuilder sb = new StringBuilder();
		Random random = new SecureRandom(); // used to
		for (int i = 0; i < codeLength; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String output = sb.toString();
		System.out.println(output);
		return output;
	}

	@Override
	public void successRegistration(String toEmail) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("findmyroom07@gmail.com", "gandhi@JI"));
		email.setStartTLSEnabled(true);
		email.setFrom("findmyroom07@gmail.com");
		email.setSubject("Welcome to FindMyRoomApp!!! Make your Travel accomadation easy");
		email.setMsg(
				"Welcome to FindMyRoomApp!!! \n We are glad you are with us.\n Now you can search for the properties you are intrested in.\n Thank you for choosing us\n");
		email.addTo(toEmail);
		email.send();
	}

}
