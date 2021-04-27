package com.example.employeeService.controller;

import com.example.employeeService.model.Employee;
import com.example.employeeService.model.Response;
import com.example.employeeService.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping(path = "/save",produces = {"application/json"})
    public Response saveEmployee(@Valid @RequestBody Employee employee) {
        Response response = employeeService.saveEmployee(employee);
        return response;
    }

    @GetMapping(path = "/get/{id}", produces =  "application/json")
    public Response getEmployee(@PathVariable Integer id) {
        List<Employee> employeeList = new ArrayList<>();
            Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);
            if (optionalEmployee.isPresent()) {
                employeeList.add(optionalEmployee.get());
            }
        Response response = new Response();
            response.setResponse(employeeList.size() >0 ? "Records Found": "Unable to find matching records");
        response.setStatus(HttpStatus.OK);
        response.setEmployee(employeeList);

        return response;
    }

    @GetMapping(path = "/getall", produces =  "application/json")
    public Response getAllEmployee() {
        List<Employee> employeeList = employeeService.getAllEmployee();

        Response response = new Response();
        response.setResponse(employeeList.size() >0 ? "Records Found": "Unable to find matching records!");

        response.setStatus(HttpStatus.OK);
        response.setEmployee(employeeList);

        return response;
    }

    @PutMapping(path = "/update", produces = "application/json")
    public Response updateEmployee(@Valid @RequestBody Employee employee) {
        return  employeeService.updateEmployee(employee);
    }

}
