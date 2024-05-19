package com.springboot.jwt.refresh.token.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.springboot.jwt.refresh.token.filter.JWTAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {
	
	@Autowired
	private JWTAuthFilter jwtAuthFilter;
	
	@Bean
	public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

}
