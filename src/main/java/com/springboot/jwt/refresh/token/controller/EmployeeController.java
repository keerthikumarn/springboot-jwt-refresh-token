package com.springboot.jwt.refresh.token.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jwt.refresh.token.dto.AuthRequest;
import com.springboot.jwt.refresh.token.dto.Employee;
import com.springboot.jwt.refresh.token.dto.JWTResponse;
import com.springboot.jwt.refresh.token.dto.RefreshTokenRequest;
import com.springboot.jwt.refresh.token.entity.EmployeeInfo;
import com.springboot.jwt.refresh.token.entity.RefreshToken;
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

	@PostMapping("/login")
	public JWTResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());
			return JWTResponse.builder().accessToken(jwtService.generateToken(authRequest.getUsername()))
					.token(refreshToken.getToken()).build();
		} else {
			throw new UsernameNotFoundException("Invalid Employee request !");
		}
	}

	@PostMapping("/refreshToken")
	public JWTResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		return refreshTokenService.findByToken(refreshTokenRequest.getToken())
				.map(refreshTokenService::verifyExpiration).map(RefreshToken::getEmpInfo).map(empInfo -> {
					String accessToken = jwtService.generateToken(empInfo.getName());
					return JWTResponse.builder().accessToken(accessToken).token(refreshTokenRequest.getToken()).build();
				}).orElseThrow(() -> new RuntimeException("Refresh token not available in the DB !!"));
	}

	@PostMapping("/signUp")
	public String addNewEmployee(@RequestBody EmployeeInfo empInfo) {
		return empService.addNewEmployee(empInfo);
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Employee> getAllEmployees() {
		return empService.getEmpList();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Employee getEmployeeById(@PathVariable int id) {
		return empService.getEmployee(id);
	}

}
