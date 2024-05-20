package com.springboot.jwt.refresh.token.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.springboot.jwt.refresh.token.dto.Employee;
import com.springboot.jwt.refresh.token.entity.EmployeeInfo;

public class EmployeeServiceTest {
	
	private EmployeeService empService = mock(EmployeeService.class);


    @Test
    public void testGetEmployee() {
        Employee emp = new Employee();
        emp.setEmpNo(1);
        emp.setName("Keerthi Kumar N");
        emp.setDesignation("Technical Architect");
        when(empService.getEmployee(1)).thenReturn(emp);
        assertEquals(emp, empService.getEmployee(1));
    }

    @Test
    public void testAddNewEmployee() {
        EmployeeInfo emp = new EmployeeInfo();
        emp.setId(123);
        emp.setName("Keerthi Kumar N");
        emp.setEmail("keerthi@no-reply.com");
        emp.setPassword("pa55w0rd");
        emp.setRoles("ADMIN_USER");
        when(empService.addNewEmployee(emp)).thenReturn("user added to system ");
        assertEquals("user added to system ", empService.addNewEmployee(emp));
    }
    @Test
    public void testGetEmpList() {
        List<Employee> empList = new ArrayList<>();
        Employee emp1 = new Employee();
        emp1.setEmpNo(1);
        emp1.setName("Keerthi Kumar N");
        emp1.setDesignation("Technical Architect");
        empList.add(emp1);

        Employee emp2 = new Employee();
        emp2.setEmpNo(2);
        emp2.setName("Raghavendra Nayak");
        emp2.setDesignation("Principal Engineer");
        empList.add(emp2);
        
        Employee emp3 = new Employee();
        emp3.setEmpNo(3);
        emp3.setName("Vineeth Kumar Bilgi");
        emp3.setDesignation("SMTS-5");
        empList.add(emp3);
        when(empService.getEmpList()).thenReturn(empList);
        assertEquals(empList, empService.getEmpList());
    }
}
