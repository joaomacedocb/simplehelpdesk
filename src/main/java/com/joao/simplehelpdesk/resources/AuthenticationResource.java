package com.joao.simplehelpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joao.simplehelpdesk.security.UserSS;
import com.joao.simplehelpdesk.services.AuthenticationService;

@RestController
public class AuthenticationResource {
	@Autowired
	AuthenticationManager authManager;
	private final AuthenticationService authenticationService;

	public AuthenticationResource(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("authenticate")
	public String authenticate(@RequestBody UserSS user) {
		System.out.println(user.getUsername());
		UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(user.getUsername(),
				user.getPassword());
		Authentication authentication = authManager.authenticate(dadosLogin);
		return authenticationService.authenticate(authentication);
	}
}