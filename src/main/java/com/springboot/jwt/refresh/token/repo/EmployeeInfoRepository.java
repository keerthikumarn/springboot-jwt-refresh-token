package com.springboot.jwt.refresh.token.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jwt.refresh.token.entity.EmployeeInfo;

public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Integer> {

	Optional<EmployeeInfo> findByName(String username);
}
