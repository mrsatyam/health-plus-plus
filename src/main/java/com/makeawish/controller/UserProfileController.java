package com.makeawish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.makeawish.model.LoginCredential;

@Controller
public class UserProfileController {

	@PostMapping("/userProfile")
	public String getUserProfile(@SessionAttribute("login") LoginCredential login, Model model) {
		System.out.println("In User Profile Controller...");
		System.out.println("Username from session object: " + login.getUsername());
		return "profile";
	}
}
