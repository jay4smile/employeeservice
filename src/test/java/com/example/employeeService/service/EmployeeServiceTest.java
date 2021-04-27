package com.example.employeeService.service;

import com.example.employeeService.model.Employee;
import com.example.employeeService.model.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;


    @Test
    @DisplayName("Save Employee success")
    public void testSaveEmployeeSuccess() {
        Employee employee = new Employee();
        employee.setFirstName("Jay");
        employee.setLastName("Patel");
        employee.setEmail("test@test.com");
        Response response = employeeService.saveEmployee(employee);

        Assertions.assertEquals("Employee details saved successfully!", response.getResponse());
    }

    @Test
    @DisplayName("Save Employee success")
    public void testSaveEmployeeFailureRecordsAlreadyPresent() {
        Employee employee = new Employee();
        employee.setFirstName("Jay");
        employee.setLastName("Patel");
        employee.setEmail("jay.patel@email.com");
        employeeService.saveEmployee(employee);

        Employee employee2 = new Employee();
        employee2.setFirstName("Jay");
        employee2.setLastName("Patel");
        employee2.setEmail("jay.patel@email.com");

        Response response = employeeService.saveEmployee(employee2);
        Assertions.assertEquals("Employee details already exists", response.getResponse());
    }

    @Test
    @DisplayName("Get All Employee")
    public void testGetAllEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Jay");
        employee.setLastName("Patel");
        employee.setEmail("jay.patel@email.com");
        employeeService.saveEmployee(employee);

        List<Employee> employeeList = employeeService.getAllEmployee();
        Assertions.assertEquals(1, employeeList.size());
    }

    @Test
    @DisplayName("Get Employee By Id")
    public void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setFirstName("Jay");
        employee.setLastName("Patel");
        employee.setEmail("jay.patel@email.com");
        employeeService.saveEmployee(employee);

        Optional<Employee> employee1 = employeeService.getEmployeeById(Integer.valueOf(1));
        Assertions.assertTrue(employee1.isPresent());
    }

    @Test
    @DisplayName("Update Details Success")
    public void testUpdateEmployeeSuccess() {
        Employee employee = new Employee();
        employee.setFirstName("Jay");
        employee.setLastName("Patel");
        employee.setEmail("jay.patel@email.com");
        employeeService.saveEmployee(employee);

        Employee employee2 = new Employee();
        employee2.setFirstName("Jaymin");
        employee2.setLastName("Patel");
        employee2.setEmail("jay.patel@email.com");
        employee2.setId(1);
        Response response = employeeService.updateEmployee(employee2);


        Assertions.assertEquals("Employee details updated successfully!", response.getResponse());
    }

    @Test
    @DisplayName("Update Details With Duplicate Email")
    public void testUpdateEmployeeWithDuplicateEmail() {
        Employee employee = new Employee();
        employee.setFirstName("Jay");
        employee.setLastName("Patel");
        employee.setEmail("jay.patel@email.com");
        employeeService.saveEmployee(employee);

        Employee employee1 = new Employee();
        employee1.setFirstName("Jay");
        employee1.setLastName("Patel");
        employee1.setEmail("test.test@email.com");
        employeeService.saveEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Jaymin");
        employee2.setLastName("Patel");
        employee2.setEmail("test.test@email.com");
        employee2.setId(1);
        Response response = employeeService.updateEmployee(employee2);


        Assertions.assertEquals("Email details already exist!", response.getResponse());
    }

    @Test
    @DisplayName("Update Details Where records not available")
    public void testUpdateEmployeeWhereRecordsNotAvailable() {
        Employee employee = new Employee();
        employee.setFirstName("Jay");
        employee.setLastName("Patel");
        employee.setEmail("jay.patel@email.com");
        employeeService.saveEmployee(employee);

        Employee employee1 = new Employee();
        employee1.setFirstName("Jay");
        employee1.setLastName("Patel");
        employee1.setEmail("test.test@email.com");
        employeeService.saveEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Jaymin");
        employee2.setLastName("Patel");
        employee2.setEmail("test.test@email.com");
        employee2.setId(3);
        Response response = employeeService.updateEmployee(employee2);


        Assertions.assertEquals("Employee record does not exist!", response.getResponse());
    }

    @Test
    @DisplayName("Update Details Where ID is not passed")
    public void testUpdateEmployeeWhereIdIsNotPresent() {
        Employee employee = new Employee();
        employee.setFirstName("Jay");
        employee.setLastName("Patel");
        employee.setEmail("jay.patel@email.com");
        employeeService.saveEmployee(employee);

        Employee employee1 = new Employee();
        employee1.setFirstName("Jay");
        employee1.setLastName("Patel");
        employee1.setEmail("test.test@email.com");
        employeeService.saveEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Jaymin");
        employee2.setLastName("Patel");
        employee2.setEmail("test.test@email.com");
        Response response = employeeService.updateEmployee(employee2);


        Assertions.assertEquals("Unable to update Employee record!", response.getResponse());
    }
}
