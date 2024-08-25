package com.sachin.binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ViewEnqFilterRequest 
{
	private String CourseName;
	private String ClassMode;
	private String status;

}
