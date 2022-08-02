package com.makeawish.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.makeawish.model.LoginCredential;
import com.makeawish.model.Users;

@ControllerAdvice
public class DefaultModelAttributeInjectorController {
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
