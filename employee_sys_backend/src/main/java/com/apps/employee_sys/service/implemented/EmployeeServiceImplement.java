package com.apps.employee_sys.service.implemented;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.employee_sys.dto.EmployeeDto;
import com.apps.employee_sys.entity.Employee;
import com.apps.employee_sys.exception.ResourceNotFoundException;
import com.apps.employee_sys.mapper.EmployeeMapper;
import com.apps.employee_sys.repository.EmployeeRepository;
import com.apps.employee_sys.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImplement implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee with id %d not found", employeeId)));
            
            return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees =  employeeRepository.findAll();
        return employees.stream()
                        .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                        .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDto) {
        Employee employee = employeeRepository.findById(employeeId)
                            .orElseThrow(() -> new ResourceNotFoundException("Employee with id doesnot exist" + employeeId) );

            employee.setFirstName(updatedEmployeeDto.getFirstName());
            employee.setLastName(updatedEmployeeDto.getLastName());
            employee.setEmail(updatedEmployeeDto.getEmail());
            employeeRepository.save(employee);

            return EmployeeMapper.mapToEmployeeDto(employee);
            // Employee updatedEmployee = EmployeeMapper.mapToEmployee(updatedEmployeeDto);
            // employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                            .orElseThrow(() -> new ResourceNotFoundException("Employee with id doesnot exist" + employeeId));

        employeeRepository.deleteById(employeeId);
    }

}
