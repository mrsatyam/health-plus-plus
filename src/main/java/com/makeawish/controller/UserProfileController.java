package com.makeawish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.makeawish.model.LoginCredential;
import com.makeawish.model.Users;
import com.makeawish.repository.UserRepository;

@Controller
public class UserProfileController {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/userProfile")
	public String getUserProfile(@SessionAttribute("login") LoginCredential login, Model model) {
		System.out.println("In User Profile Controller...");
		System.out.println("Username from session object: " + login.getUsername());
		Users user = userRepository.searchByUsername(login.getUsername());
		model.addAttribute("user", user);
		return "profile";
	}
}
