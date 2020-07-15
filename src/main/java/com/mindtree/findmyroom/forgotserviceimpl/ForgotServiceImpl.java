package com.mindtree.findmyroom.forgotserviceimpl;



import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.findmyroom.exception.InvalidEmailException;
import com.mindtree.findmyroom.forgotpasswordrepository.ForgotPasswordRepository;
import com.mindtree.findmyroom.forgotserviceint.ForgotServiceInt;


@Service
public class ForgotServiceImpl implements ForgotServiceInt {

	
	@Autowired
	ForgotPasswordRepository frpo;
	
	
	@Override
	public String CheckUser(String userEmail) throws InvalidEmailException 
	{
		
		
		String  username ;
		
					username = frpo.validEmail(userEmail);
					
		if(username== null)
		{
			throw new InvalidEmailException("Invalid User Name");  
		}
		else
		{
			String userpassword = frpo.userPassword(username);
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("findmyroom07@gmail.com", "gandhi@JI"));
		email.setStartTLSEnabled(true);
		try {
			email.setFrom("findmyroom07@gmail.com");
		} catch (EmailException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}
		email.setSubject("Hello User! Here is Your Password . Login now to access your account ");
		try {
			email.setMsg("Hello User! We have sent your Password . Kindly login with your credentials along with password sent\n" + "Your Password:"  + userpassword + "\n" +"Thank you");
			email.addTo(username);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		try {
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return "password sent successfully as email";
		}
						
		
	}
	
}
