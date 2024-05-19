package com.springboot.jwt.refresh.token.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springboot.jwt.refresh.token.entity.UserInfo;
import com.springboot.jwt.refresh.token.repo.UserInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserInfoRepository userInfoRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = userInfoRepo.findByName(username);
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User : " + username + " not found"));
	}

}
