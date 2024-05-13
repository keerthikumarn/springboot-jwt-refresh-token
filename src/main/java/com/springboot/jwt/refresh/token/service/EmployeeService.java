package com.springboot.jwt.refresh.token.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.jwt.refresh.token.dto.Employee;
import com.springboot.jwt.refresh.token.repo.UserInfoRepository;

@Service
public class EmployeeService {

	private List<Employee> empList = null;

	@Autowired
	private UserInfoRepository userInfoRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Employee> getEmpList() {
		return empList;
	}
	
	public Employee getEmployee(int id) {
		return empList.stream()
                .filter(emp -> emp.getEmpNo() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Employee with id: " + id + " not found"));
	}
}
