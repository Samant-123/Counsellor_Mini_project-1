package com.sachin.binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class DashboardResponse
{
	private Integer totalEnqrs;
	
	private Integer openEnqrs;
	
	private Integer enrolledEnqrs;
	
	private Integer lostEnqrs;

}
