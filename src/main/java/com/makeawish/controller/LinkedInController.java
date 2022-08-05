package com.makeawish.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LinkedInController {
	/*
	 * @GetMapping("/linkedin") public void redirectToLinkedIn(HttpServletResponse
	 * response) throws IOException {
	 * System.out.println("Redirecting to LinkedIn...");
	 * response.sendRedirect("https://www.linkedin.com/in/mrsatyamjoshi/");
	 * 
	 * }
	 */
	
	@GetMapping("/linkedin")
	public String redirectToLinkedIn(HttpServletResponse response) throws IOException {
		System.out.println("Redirecting to LinkedIn...");
		return "redirect:https://www.linkedin.com/in/mrsatyamjoshi/";
	}
	
}
