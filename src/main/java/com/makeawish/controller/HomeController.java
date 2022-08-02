package com.makeawish.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.makeawish.model.LoginCredential;
import com.makeawish.model.Users;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String goHome() {
		System.out.println("In the home controller");
		return "index";
	}

	@GetMapping("/goToSearch")
	public String goToSearch() {
		System.out.println("going to search page");
		return "search";
	}

	@GetMapping("/goToLogin")
	public String goToLogin() {
		System.out.println("going to login page");
		return "login";
	}

	@GetMapping("/goToRegistration")
	public String goToRegistration() {
		System.out.println("going to registration page");
		return "register";
	}

	@ModelAttribute("newUser")
	public Users getDefaultUser() {
		return new Users();
	}

	@ModelAttribute("genderItems")
	public List<String> getGenderItems() {
		return Arrays.asList("Male", "Female", "Other");
	}

	@ModelAttribute("login")
	public LoginCredential getDefaultCredentials() {
		return new LoginCredential();
	}

}
