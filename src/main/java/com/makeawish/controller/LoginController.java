package com.makeawish.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.makeawish.exception.ApplicationException;
import com.makeawish.model.LoginCredential;
import com.makeawish.model.Users;
import com.makeawish.repository.UserRepository;

@Controller
@SessionAttributes("login")
public class LoginController {
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/goToLogin")
	public Callable<String> login(@ModelAttribute("login") LoginCredential credential, Model model) {
		Users user = userRepository.searchByUsername(credential.getUsername());
		if (user == null) {
			throw new ApplicationException("User was not found");
		}
		return () -> "forward:/userProfile";
	}

	@ExceptionHandler(ApplicationException.class)
	public String handleException() {
		System.out.println("In Exception handler of Login Controller");
		return "error";
	}

}
