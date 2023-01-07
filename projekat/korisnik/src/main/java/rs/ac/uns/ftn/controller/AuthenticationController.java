package rs.ac.uns.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.model.LoginData;
import rs.ac.uns.ftn.model.User;
import rs.ac.uns.ftn.service.AuthenticationService;

@RestController
@RequestMapping(value="/auth", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/login")
	public User login(@RequestBody LoginData loginData) {
		return authenticationService.createAuthenticationToken(loginData);
	}
}
