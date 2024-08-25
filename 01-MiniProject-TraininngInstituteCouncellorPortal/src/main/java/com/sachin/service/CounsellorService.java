package com.sachin.service;

import com.sachin.binding.DashboardResponse;
import com.sachin.entity.Counsellor;

public interface CounsellorService 
{
	public Counsellor login(String email, String pwd);
	
	public boolean registration(Counsellor counsellor);
	
	public DashboardResponse getDashboardResponse(Integer CouncellorId);
	
	public Counsellor findByEmail(String email);

}
