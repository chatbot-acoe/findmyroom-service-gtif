package com.mindtree.findmyroom.service.implementation;

import java.util.List;
import java.util.Optional;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.findmyroom.entity.Flat;
import com.mindtree.findmyroom.entity.FlatBuy;
import com.mindtree.findmyroom.entity.PGRent;
import com.mindtree.findmyroom.entity.PayingGuest;
import com.mindtree.findmyroom.entity.User;
import com.mindtree.findmyroom.repository.AdminDashboardRepository;
import com.mindtree.findmyroom.repository.FlatBuyRepository;
import com.mindtree.findmyroom.repository.FlatRepository;
import com.mindtree.findmyroom.repository.PgRentRepository;
import com.mindtree.findmyroom.repository.PgRepository;
import com.mindtree.findmyroom.service.Interface.AdminDashboardService;

/**
 * @author M1048973
 *
 */
@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {

	@Autowired
	AdminDashboardRepository adminDashboardRepository;
	
	@Autowired
	FlatBuyRepository flatBuyRepository;
	
	@Autowired
	FlatRepository flatRepository;
	
	@Autowired
	PgRepository pgRepository;
	
	@Autowired
	PgRentRepository pgRentRepository;
	
	
	@Override
	public List<User> getPartnerDetails() {
		
		List<User> partnerList=(List<User>) adminDashboardRepository.findUserByUserType("partner");
		partnerList.forEach(p->p.setUserPassword("xxxxxxxx"));
		return partnerList;
	}

	@Override
	public boolean removePartner(int userId) {
		
		List<FlatBuy> flatbuylist=flatBuyRepository.findAll();
		List<PGRent> pgrentlist=pgRentRepository.findAll();
		
		for(FlatBuy f:flatbuylist)
		{
			if(f.getPartner().getUserId()==userId)
			{
				return false;
			}
		}
		

		for(PGRent p:pgrentlist)
		{
			if(p.getPartner().getUserId()==userId)
			{
				return false;
			}
		}
		
		List<Flat> flatlist=flatRepository.findAll();
		
		for(Flat f:flatlist)
		{
			if(f.getUser().getUserId()==userId)
			{
				flatRepository.delete(f);
			}
		}		
		
		List<PayingGuest> pglist=pgRepository.findAll();
		for(PayingGuest pg:pglist)
		{
			if(pg.getUser().getUserId()==userId)
			{
				pgRepository.delete(pg);
			}
		}
		
		adminDashboardRepository.deleteById(userId);
		
    	return true;	
	}
	
	
	public User verifyPartnerService(int userId)
	{
	   User partner=adminDashboardRepository.getOne(userId);
	   if(partner==null)
		   return null;
	   partner.setVerified(true);
	 
	   adminDashboardRepository.save(partner);
		
		return partner;
		
	}
	
	
	public void successVerify(String toEmail) throws EmailException  {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("findmyroom07@gmail.com", "gandhi@JI"));
		email.setStartTLSEnabled(true);
		email.setFrom("findmyroom07@gmail.com");
		email.setSubject("Verification mail");
		email.setMsg(
				"Congrats!!!!!!!\nNow you are a verified partner. Expand your business easily with us as a verified partner.\nThank you for choosing us..\n");
		email.addTo(toEmail);
		email.send();
	}
	
}
