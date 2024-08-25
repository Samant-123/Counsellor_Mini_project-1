package com.sachin.service;

import java.util.List;

import com.sachin.binding.DashboardResponse;
import com.sachin.binding.ViewEnqFilterRequest;
import com.sachin.entity.Enquiry;

public interface EnquiryService 
{

	public boolean addEnquiry(Enquiry enquiry, Integer CouncellorId);
	
	public List<Enquiry> getAllEnquiries(Integer CouncellorId);
	
	public List<Enquiry>  getEnquiriesWithFilter(ViewEnqFilterRequest filterreq, Integer CouncellorId);
	
	public Enquiry getEnquiryByID(Integer enqId);

}
