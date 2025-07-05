package com.apps.employee_sys;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.apps.employee_sys.service.EmployeeService;

@SpringBootTest
class EmployeeSysApplicationTests {

	// @Autowired
	// EmployeeService employeeService;

	@Test
	void contextLoads() {
	}

	@Test
	public void forInvalidId_thenReturnsFalse() {

	}
}
