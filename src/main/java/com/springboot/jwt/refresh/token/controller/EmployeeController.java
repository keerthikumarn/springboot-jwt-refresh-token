package com.springboot.jwt.refresh.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jwt.refresh.token.entity.EmployeeInfo;
import com.springboot.jwt.refresh.token.service.EmployeeService;
import com.springboot.jwt.refresh.token.service.JWTService;
import com.springboot.jwt.refresh.token.service.RefreshTokenService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
    private EmployeeService empService;
	
    @Autowired
    private JWTService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/signUp")
    public String addNewEmployee(@RequestBody EmployeeInfo empInfo) {
    	return empService.addNewEmployee(empInfo);
    }
    
}
