package com.makeawish.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.makeawish.model.Users;
import com.makeawish.repository.UserRepository;

@Controller
public class RegistrationController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/registerUser")
	public String registerUser(@Valid @ModelAttribute("newUser") Users newUser, BindingResult result, Model model) {
		System.out.println(newUser);
		System.out.println(newUser.getDateOfBirth());
		System.out.println("Errors: " + result.getAllErrors());
		if (result.hasErrors()) {
			return "register";
		}
		userRepository.save(newUser);
		model.addAttribute("registerSuccess", "User registered successfully");
		return "login";
	}

}
