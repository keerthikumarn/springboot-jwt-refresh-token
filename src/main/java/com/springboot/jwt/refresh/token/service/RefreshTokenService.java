package com.springboot.jwt.refresh.token.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jwt.refresh.token.entity.RefreshToken;
import com.springboot.jwt.refresh.token.repo.EmployeeInfoRepository;
import com.springboot.jwt.refresh.token.repo.RefreshTokenRepository;

@Service
public class RefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private EmployeeInfoRepository empInfoRepository;

	public RefreshToken createRefreshToken(String empName) {
		RefreshToken refreshToken = RefreshToken.builder().empInfo(empInfoRepository.findByName(empName).get())
				.token(UUID.randomUUID().toString()).expiryDate(Instant.now().plusMillis(600000)).build();
		return refreshTokenRepository.save(refreshToken);
	}

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new RuntimeException(
					token.getToken() + " Refresh token has been expired. Please make a new signin request");
		}
		return token;
	}
}
