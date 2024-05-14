package com.springboot.jwt.refresh.token.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jwt.refresh.token.entity.RefreshToken;
import com.springboot.jwt.refresh.token.repo.RefreshTokenRepository;
import com.springboot.jwt.refresh.token.repo.UserInfoRepository;

@Service
public class RefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	public RefreshToken createRefreshToken(String username) {
		RefreshToken refreshToken = RefreshToken.builder()
				.userInfo(userInfoRepository.findByName(username).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))//10
                .build();
        return refreshTokenRepository.save(refreshToken);
		return null;
	}
	
	
}
