package com.example.employeeService.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class Response {
    private String response;
    private HttpStatus status;
    private List<Employee> employee;
}
