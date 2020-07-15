package com.mindtree.findmyroom.service.Interface;

import java.util.List;

import org.apache.commons.mail.EmailException;

import com.mindtree.findmyroom.entity.User;

public interface AdminDashboardService {

	List<User> getPartnerDetails();
	boolean removePartner(int userId);
	void successVerify(String toEmail) throws EmailException;
	User verifyPartnerService(int userId);
}
