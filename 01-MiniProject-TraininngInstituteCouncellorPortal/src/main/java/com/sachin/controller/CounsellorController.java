package com.sachin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sachin.binding.DashboardResponse;
import com.sachin.entity.Counsellor;
import com.sachin.service.CounsellorServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CounsellorController {
	private CounsellorServiceImpl counsellorService;

	@GetMapping("/")
	public String index(Model model) {
		Counsellor coun = new Counsellor();
		// sending data from controller to Ui
		model.addAttribute("counsellor", coun);
		return "index";
	}

//Handling Login Functionality
	@PostMapping("/login")
	public String login(Counsellor counsellor, HttpServletRequest req, Model model) {
		Counsellor cobj = counsellorService.login(counsellor.getEmail(), counsellor.getPwd());

		if (cobj == null) {
			model.addAttribute("errmsg", "Invalid Credentail");
			return "index";
		} else {
			// valid login and store counsellor id in session for Future Purpose
			HttpSession session = req.getSession(true);
			session.setAttribute("counsellorid", cobj.getCounsellor_id());

			DashboardResponse dobj = counsellorService.getDashboardResponse(cobj.getCounsellor_id());
			model.addAttribute("dashboardresponse", dobj);
			return "dashboard";
		}

	}

	// To see Dash board Page
	@GetMapping("/dashboard")
	public String displayDashboard(HttpServletRequest req, Model model) {
		// Get existing Session Object
		HttpSession session = req.getSession(false);
		// We are getting the counselor ID
		Integer counsellorId = (Integer) session.getAttribute("counsellorid");// counsellorid

		DashboardResponse dobj = counsellorService.getDashboardResponse(counsellorId);
		model.addAttribute("dashboardresponse", dobj);
		return "dashboard";
	}

	// Logout
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		// get existing session and invalidate it
		HttpSession session = req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	// This method for display Registration page

	@GetMapping("/register")
	public String registerPage(Model model) {
		Counsellor coun = new Counsellor();
		// sending data from controller to UI
		model.addAttribute("counsellor", coun);
		return "register";

	}

	// Handle the Registration Page
	@PostMapping("/register")
	public String handleRegistration(Counsellor counsellor, Model model) {
		Counsellor byEmail = counsellorService.findByEmail(counsellor.getEmail());
		if (byEmail != null) {
			model.addAttribute("errmsg", "Duplicate Email...!");
			return "register";
		}
		boolean isRegister = counsellorService.registration(counsellor);
		if (isRegister) {
			model.addAttribute("scsmsg", "Registration Success...!");

		} else {
			model.addAttribute("errmsg", "Registration Failure...!");
		}

		return "register";

	}

}
