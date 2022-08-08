package com.makeawish.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.makeawish.exception.LoginFailureException;
import com.makeawish.model.LoginCredential;

@RestController
public class LoginRestController {

	@PostMapping(value = "/makeawish/rest/login")
	public ResponseEntity<String> login(@RequestBody LoginCredential credential)
			throws URISyntaxException, IOException, InterruptedException, LoginFailureException {
		HttpRequest httpRequest = HttpRequest.newBuilder(new URI("http://localhost:8080/makeawish/rest/user/"
				+ credential.getUsername() + "/" + credential.getPassword())).GET().build();
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> httpResponse = client.send(httpRequest, BodyHandlers.ofString());
		System.out.println(httpResponse);

		switch (HttpStatus.valueOf(httpResponse.statusCode())) {
		case OK:
			return new ResponseEntity<>("Logged in successfully...", HttpStatus.OK);
		case NOT_FOUND:
			return new ResponseEntity<>("User not registered...", HttpStatus.NOT_FOUND);
		case FORBIDDEN:
			throw new LoginFailureException("Invalid username or password...");
			//return new ResponseEntity<>("Invalid Credentials...", HttpStatus.FORBIDDEN);
		default:
			break;
		}
		return null;
	}

}
