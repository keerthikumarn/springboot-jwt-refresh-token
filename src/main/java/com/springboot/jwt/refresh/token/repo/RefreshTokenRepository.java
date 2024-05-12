package com.springboot.jwt.refresh.token.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.jwt.refresh.token.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer>{

	Optional<RefreshToken> findByToken(String token);
}
