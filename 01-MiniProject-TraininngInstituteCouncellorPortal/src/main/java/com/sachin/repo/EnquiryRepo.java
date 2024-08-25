package com.sachin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sachin.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> 
{

	
	@Query(value = "SELECT * FROM Enquiry  where counsellor_id=:councellor_id" ,nativeQuery = true)
	public List<Enquiry> getEnquiriesByCouncellor_id(Integer councellor_id);
}
