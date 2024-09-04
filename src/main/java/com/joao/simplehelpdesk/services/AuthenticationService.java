package com.joao.simplehelpdesk.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.joao.simplehelpdesk.security.JwtService;

@Service
public class AuthenticationService {
	
	private final JwtService jwtService;

	public AuthenticationService(JwtService jwtService) {
		super();
		this.jwtService = jwtService;
	}
	
	public String authenticate(Authentication authentication) {
		return jwtService.generateToken(authentication);
	}
	
}
