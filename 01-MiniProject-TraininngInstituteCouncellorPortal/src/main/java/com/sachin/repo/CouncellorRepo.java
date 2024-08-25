package com.sachin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.entity.Counsellor;

public interface CouncellorRepo extends JpaRepository<Counsellor, Integer> 
{
	
	public  Counsellor  findByEmailAndPwd(String email, String Pwd) ;
	
	//select * from Councellor_tbl where email=:email
	public Counsellor findByEmail(String email);

}
