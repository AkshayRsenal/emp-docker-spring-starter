package com.apps.employee_sys.integration.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.apps.employee_sys.controller.EmployeeController;
import com.apps.employee_sys.dto.EmployeeDto;
import com.apps.employee_sys.exception.ResourceNotFoundException;
import com.apps.employee_sys.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    public void forValidId_shouldReturn200_True() throws Exception {
        Long employeeId = 8L;

        EmployeeDto employeeDto = new EmployeeDto(employeeId, "Anuj", "Dhavan", "any@example.com");

        // Configure to return a valid EmployeeDto for the given id
        given(employeeService.getEmployeeById(employeeId))
                .willReturn(employeeDto);

        mockMvc.perform(get("/api/v1/employees/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void forInvalidId_thenReturnsTrue() throws Exception {
        Long employeeId = 10L;

        // Configure to throw ResourceNotFoundException for invalid id
        given(employeeService.getEmployeeById(employeeId))
                .willThrow(new ResourceNotFoundException(String.format("Employee with id %d not found", employeeId)));

        mockMvc.perform(get("/api/v1/employees/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
