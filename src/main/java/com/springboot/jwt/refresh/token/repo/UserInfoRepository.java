package com.springboot.jwt.refresh.token.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.jwt.refresh.token.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	Optional<UserInfo> findByName(String username);
}
