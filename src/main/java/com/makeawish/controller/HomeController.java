package com.makeawish.controller;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public Callable<String> goHome(HttpServletRequest httpServletRequest) {
		System.out.println("In the home controller");
		System.out.println("Is Async supported: " + httpServletRequest.isAsyncSupported());
		System.out.println(Thread.currentThread().getName());
		return () -> "index";

	}

	@GetMapping("/goToSearch")
	public Callable<String> goToSearch() {
		System.out.println("going to search page");
		return () -> "search";
	}

	@GetMapping("/goToLogin")
	public Callable<String> goToLogin() {
		System.out.println("going to login page");
		return () -> "login";
	}

	@GetMapping("/goToRegistration")
	public Callable<String> goToRegistration() {
		System.out.println("going to registration page");
		return () -> "register";
	}

}
