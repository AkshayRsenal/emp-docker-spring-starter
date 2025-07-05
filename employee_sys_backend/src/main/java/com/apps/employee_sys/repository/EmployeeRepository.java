package com.apps.employee_sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.employee_sys.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
