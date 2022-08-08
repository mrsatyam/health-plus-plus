package com.makeawish.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/makeawish/rest/user/{username}/{password}")
	public ResponseEntity<Users> getUsersByPathVariable(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		System.out.println("Control got here...");
		Users user = userRepository.searchByUsername(username);
		if (user == null) {
			return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
		} else if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(user, HttpStatus.FORBIDDEN);
		}
	}
}
