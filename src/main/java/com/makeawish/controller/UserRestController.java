package com.makeawish.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makeawish.model.Users;
import com.makeawish.repository.UserRepository;

@RestController
public class UserRestController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/makeawish/rest/users")
	public List<Users> getUsers() {
		List<Users> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
}
