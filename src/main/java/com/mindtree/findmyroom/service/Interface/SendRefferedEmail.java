package com.mindtree.findmyroom.service.Interface;

import java.util.List;

import com.mindtree.findmyroom.entity.FlatBuy;
import com.mindtree.findmyroom.entity.FlatBuyDto;
import com.mindtree.findmyroom.entity.PGRentDto;
import com.mindtree.findmyroom.exception.InvalidEmailException;
import com.mindtree.findmyroom.exception.UserExistsException;

public interface SendRefferedEmail {

	String sendmail(String userName, String userEmail, String userReferral, String referredEmailId) throws InvalidEmailException ,UserExistsException;

	List<FlatBuyDto> getbookindetailsf(int userId);

	List<PGRentDto> getbookindetailsp(int userId);


	

	
}
