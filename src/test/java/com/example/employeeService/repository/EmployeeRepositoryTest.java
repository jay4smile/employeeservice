package com.example.employeeService.repository;


import com.example.employeeService.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Find by email")
    public void testFindByEmail() {
        List<Employee> employeeList= employeeRepository.findByEmail("jay.patel@email.com");
        Assertions.assertNotNull(employeeList);
        Assertions.assertNotEquals(1, employeeList.size());
    }

    @Test
    @DisplayName("Find by ID")
    public void testFindByID() {
        Optional<Employee> employee = employeeRepository.findById(Integer.valueOf(1));
        Assertions.assertNotNull(employee);
        Assertions.assertNotEquals(true, employee.isPresent());
    }
}
