package com.springboot.jwt.refresh.token.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.jwt.refresh.token.service.JWTService;

@Component
public class JWTAuthFilter {

	//TODO: yet to finish off the complete implementation
	
	@Autowired
	private JWTService jwtService;
	
}
