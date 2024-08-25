package com.sachin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sachin.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import com.sachin.binding.ViewEnqFilterRequest;
import com.sachin.entity.Enquiry;

@Controller
@AllArgsConstructor

public class EnquiryController {

	private EnquiryService enqService;

	// Loading Enquiry Form
	@GetMapping("/enquiry")
	public String addEnquiry(Model model) {
		// for binding with Enquiry Object
		Enquiry enqObj = new Enquiry();
		model.addAttribute("enq", enqObj);
		return "enquiryform";
	}

	// Handling AddEnquiry Button
	@PostMapping("/addenq")
	public String handleAddEnquiry(@ModelAttribute("enq") Enquiry enq, HttpServletRequest req, Model model) {
		// Get existing Session Object
		HttpSession session = req.getSession(false);
		// We are getting the counselor ID
		Integer counsellorId = (Integer) session.getAttribute("counsellorid");// counsellorid

		boolean isSave = enqService.addEnquiry(enq, counsellorId);

		if (isSave) {
			model.addAttribute("smsg", "Enquiry Added Successfully");
		} else {
			model.addAttribute("emsg", "Failed to add the Enquiry>>>!!");
		}
		return "enquiryform";
	}
	//Handle Edit Option in Enquiry Form
	@GetMapping("/editEnq")
	public String editEnquiry(Integer enqId,Model model)
	{
		Enquiry  enqObj= enqService.getEnquiryByID(enqId);
		model.addAttribute("enq", enqObj);
		return "enquiryform";
		
	}

	// Get All Enquiry Details
	@GetMapping("/view-enquiries")
	public String getEnquiry(HttpServletRequest req, Model model) {
		// Get existing Session Object
		HttpSession session = req.getSession(false);
		// We are getting the counselor ID
		Integer counsellorId = (Integer) session.getAttribute("counsellorid");

		List<Enquiry> enqList = enqService.getAllEnquiries(counsellorId);
		model.addAttribute("enquiries", enqList);
		//Filterrequest
		 ViewEnqFilterRequest filterRequest = new ViewEnqFilterRequest();
		model.addAttribute("viewEnqFilterRequest", filterRequest);

		return "viewEnqPage";

	}
//TO Handle Search Button
@GetMapping("/filter-enq")
	public String filterEnquiry( ViewEnqFilterRequest viewEnqFilterRequest,HttpServletRequest req, Model model)
	{
		// Get existing Session Object
				HttpSession session = req.getSession(false);
				// We are getting the counselor ID
				Integer counsellorId = (Integer) session.getAttribute("counsellorid");

				List<Enquiry> enqList = enqService.getEnquiriesWithFilter(viewEnqFilterRequest, counsellorId);
				model.addAttribute("enquiries", enqList);
				
				return "viewEnqPage";
	}
}
