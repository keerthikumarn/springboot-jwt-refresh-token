package com.springboot.jwt.refresh.token.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.jwt.refresh.token.dto.Employee;
import com.springboot.jwt.refresh.token.entity.EmployeeInfo;
import com.springboot.jwt.refresh.token.repo.EmployeeInfoRepository;

import jakarta.annotation.PostConstruct;

@Service
public class EmployeeService {

	private List<Employee> empList = null;

	@Autowired
	private EmployeeInfoRepository userInfoRepo;

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
	
	public String addNewEmployee(EmployeeInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepo.save(userInfo);
        return "user added to system ";
    }
	
	@PostConstruct
	public void loadEmployeesFromDB() {
		empList = IntStream.rangeClosed(1, 100)
				.mapToObj(
						index -> Employee.builder().empNo(index).name("user_" + index).designation("tech arch").build())
				.collect(Collectors.toList());
	}
}
