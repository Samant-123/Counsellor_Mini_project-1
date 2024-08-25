package com.sachin.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.sachin.binding.ViewEnqFilterRequest;
import com.sachin.entity.Counsellor;
import com.sachin.entity.Enquiry;
import com.sachin.repo.CouncellorRepo;
import com.sachin.repo.EnquiryRepo;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class EnquiryServiceImpl implements EnquiryService 

{
	
	private EnquiryRepo enqRepo;

	private CouncellorRepo coun_Repo;
	
	@Override
	public boolean addEnquiry(Enquiry enquiry, Integer CouncellorId) 
	{
		Counsellor councellor = coun_Repo.findById(CouncellorId)
		.orElseThrow(()->new RuntimeException("Concellor Not Found..!!!"));
		enquiry.setCounsellor(councellor);
		Enquiry save = enqRepo.save(enquiry);
		if(save.getEnqid()!=null)return true;
		return false;
	}

	@Override
	public List<Enquiry> getAllEnquiries(Integer CouncellorId) {
	return enqRepo.getEnquiriesByCouncellor_id(CouncellorId);
	}
	//for edit hyperLink
	@Override
	public Enquiry getEnquiryByID(Integer enqId) {
		
		return enqRepo.findById(enqId).orElse(null);
	}

	@Override
	public List<Enquiry> getEnquiriesWithFilter(ViewEnqFilterRequest filterreq, Integer CouncellorId) {
	Enquiry enq = new Enquiry();
	if(StringUtils.isNotEmpty(filterreq.getClassMode())) {
		filterreq.setClassMode(filterreq.getClassMode());
	}
	if(StringUtils.isNotEmpty(filterreq.getCourseName())) {
		filterreq.setCourseName(filterreq.getCourseName());
	}
	if(StringUtils.isNotEmpty(filterreq.getStatus())) {
		filterreq.setStatus(filterreq.getStatus());
	}
	
	Counsellor councellor = coun_Repo.findById(CouncellorId).orElse(null);
	enq.setCounsellor(councellor);
	Example<Enquiry> of = Example.of(enq);//QBE Implementation(Generate Dynamic Query)
	List<Enquiry> enqList = enqRepo.findAll(of);
		return enqList;
	}

	

	

}
