package com.sachin.entity;

import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class Enquiry 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer enqid;
	private String studentname;
	private Long studentphno;
	private String coursename;
	private String classmode;
	private String enqstatus;

	 @Column(updatable =false)
	@CreationTimestamp
	private LocalDate Created_date;
	 
	 @Column(insertable =false)
		@CreationTimestamp
		private LocalDate Updated_date;
	 
	 @ManyToOne
		@JoinColumn(name="counsellor_id")
	 private Counsellor counsellor;

}
