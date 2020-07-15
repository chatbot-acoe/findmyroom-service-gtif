package com.mindtree.findmyroom.service.implementation;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mindtree.findmyroom.entity.FlatBuy;
import com.mindtree.findmyroom.entity.FlatBuyDto;
import com.mindtree.findmyroom.entity.PGRent;
import com.mindtree.findmyroom.entity.PGRentDto;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.exception.InvalidEmailException;
import com.mindtree.findmyroom.exception.UserExistsException;
import com.mindtree.findmyroom.forgotpasswordrepository.ForgotPasswordRepository;
import com.mindtree.findmyroom.repository.FlatBuyRepository;
import com.mindtree.findmyroom.repository.PgRentRepository;
import com.mindtree.findmyroom.repository.UserRepository;
import com.mindtree.findmyroom.service.Interface.SendRefferedEmail;

@Service
public class SendRefferedEmailImpl implements SendRefferedEmail  {

	@Autowired
	PgRentRepository pgre; 
	@Autowired
	FlatBuyRepository fbr;
	@Autowired
	UserRepository urpo;
	@Autowired
	ForgotPasswordRepository frpo;
	
	
	@Override
	public String sendmail(String userName, String userEmail, String userReferral, String referredEmailId) throws UserExistsException
			,InvalidEmailException {
		User user = new User();
		user = urpo.getUser(userEmail);
		User refuser = new User();
		refuser = urpo.getUser(referredEmailId);
		
			if(refuser!=null)
			{
				throw new UserExistsException("mailalready");
			}
		
		if(user.getUserWallet()>=500)
		{
			user.setUserWallet(500);
			urpo.save(user); 
		}
		else
		{
		user.setUserWallet(user.getUserWallet() + 100);
		urpo.save(user);
		}
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("findmyroom07@gmail.com", "gandhi@JI"));
		email.setStartTLSEnabled(true);
		try {
			email.setFrom("findmyroom07@gmail.com");
		}
		catch (EmailException e1) {
			
			e1.getMessage();
		}
		email.setSubject("Hello Sir/Madam , greetings from FindMyRoom");
		try {
			email.setMsg("Hello Sir/Madam , you have been reffered from the user\t" +userName + "\twith emailid\t" +userEmail + "\tkindly use the code\t" +userReferral + "\t to get exciting offers in our website\nSignUp now\n" +"Thankyou");
			email.addTo(referredEmailId);
		} catch (EmailException e) {
			
			e.getMessage();
		}
		
		try {
			email.send();
		} catch (EmailException e) {
			
			e.getMessage();
		}
		return "emailsent";
		}


	@Override
	public List<FlatBuyDto> getbookindetailsf(int userId) {
		List<FlatBuy> flatbuylist = new ArrayList<>();
		flatbuylist = fbr.getdetails(userId);
		
		List<FlatBuyDto> flatbuydto = new ArrayList<>();
		
		for(FlatBuy fbl : flatbuylist )
		{
			FlatBuyDto flatbuyd = new FlatBuyDto();
			flatbuyd.setFlatName(fbl.getFlat().getFlatName());
			flatbuyd.setBuyedDate(fbl.getDateOfBooking());
			flatbuyd.setOwnerName(fbl.getPartner().getUserFirstName());
			flatbuyd.setPrice(fbl.getFlat().getFlatPrice());
			flatbuydto.add(flatbuyd);
		}
		return flatbuydto;
	}


	@Override
	public List<PGRentDto> getbookindetailsp(int userId) {
		List<PGRent> pgrentlist = new ArrayList<>();
		pgrentlist = pgre.getdetails(userId);
		
		List<PGRentDto> pgrentd = new ArrayList<>();
		for(PGRent pg : pgrentlist )
		{
			PGRentDto pgdt = new PGRentDto();
			pgdt.setPgName(pg.getPayinGuest().getPgName());
			pgdt.setBuyedDate(pg.getDateOfBooking());
			pgdt.setOwnerName(pg.getPartner().getUserFirstName());
			pgdt.setPrice(pg.getPayinGuest().getPgPrice());
			pgrentd.add(pgdt);
			
		}
	
		return pgrentd;
	}

	
						
	
}