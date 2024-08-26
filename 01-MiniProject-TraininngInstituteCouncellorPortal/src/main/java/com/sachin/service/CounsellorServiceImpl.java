package com.sachin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachin.binding.DashboardResponse;
import com.sachin.entity.Counsellor;
import com.sachin.entity.Enquiry;
import com.sachin.repo.CouncellorRepo;
import com.sachin.repo.EnquiryRepo;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class CounsellorServiceImpl implements CounsellorService 
{
	
	private CouncellorRepo councellorRepo;
	
	private EnquiryRepo enquiryRepo;

	@Override
	public Counsellor login(String email, String pwd) {
		Counsellor councellor = councellorRepo.findByEmailAndPwd(email, pwd);
		return councellor;
	}

	@Override
	public boolean registration(Counsellor councellor) {
		Counsellor SaveObj = councellorRepo.save(councellor);
		if(SaveObj.getCounsellor_id()!=null)return true;
		return false;
	}

	@Override
	public DashboardResponse getDashboardResponse(Integer CouncellorId) 
	{
		DashboardResponse response = new DashboardResponse();
		List<Enquiry> enqList = enquiryRepo.getEnquiriesByCouncellor_id(CouncellorId);
		int totalenq = enqList.size();
		int enrolled = enqList.stream()
					.filter(e-> e.getEnqstatus().equals("Enrolled"))
									.collect(Collectors.toList()).size();

		int lost = enqList.stream()
				.filter(e-> e.getEnqstatus().equals("Lost"))
				.collect(Collectors.toList()).size();
		int opened = enqList.stream()
				.filter(e-> e.getEnqstatus().equals("Open"))
				.collect(Collectors.toList()).size();
		response.setTotalEnqrs(totalenq);
		response.setEnrolledEnqrs(enrolled);
		response.setLostEnqrs(lost);
		response.setOpenEnqrs(opened);
		return response;
		
		
	}
	
	@Override
	public Counsellor findByEmail(String email) {
		return councellorRepo.findByEmail(email);
		
	}

	

	
}
