package com.springboot.jwt.refresh.token.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springboot.jwt.refresh.token.entity.EmployeeInfo;
import com.springboot.jwt.refresh.token.repo.EmployeeInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private EmployeeInfoRepository empInfoRepo;

	@Override
	public UserDetails loadUserByUsername(String empName) throws UsernameNotFoundException {
		Optional<EmployeeInfo> userInfo = empInfoRepo.findByName(empName);
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Employee : " + empName + " not found"));
	}

}
