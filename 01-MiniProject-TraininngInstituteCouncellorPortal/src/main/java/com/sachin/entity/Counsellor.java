package com.sachin.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data	
@Entity
public class Counsellor
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer counsellor_id;
	
	
	@NotEmpty(message = "Should Not Be Empty")
	private String name;
	
	//@Email(message = "Email should be mandaory")
	@Column(unique =true)
	private String email;
	
	//@NotBlank(message = "Password must")
	private String pwd;
	//@NotBlank(message = "Phone Number  Must")
	private Long phnno;
	
	 @Column(updatable =false)
	@CreationTimestamp
	private LocalDate Created_date;
	 
	 @Column(insertable =false)
		@CreationTimestamp
		private LocalDate Updated_date;

}
