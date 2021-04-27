package com.example.employeeService.controller;



import com.example.employeeService.model.Employee;
import com.example.employeeService.model.Response;
import com.example.employeeService.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@AutoConfigureMockMvc()
@WebMvcTest()
public class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach()
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test()
    @DisplayName("Save Employee")
    public void saveEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmail("test.test@email.com");
        employee.setFirstName("Jay");
        employee.setLastName("Patel");

        ObjectMapper mapper = new ObjectMapper();

        Response responseObj = new Response();
        responseObj.setStatus(HttpStatus.OK);

        when(employeeService.saveEmployee(employee)).thenReturn(responseObj);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                .content(mapper.writeValueAsString(employee)).contentType(MediaType.APPLICATION_JSON)).andReturn();

        Response response = mapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatus());

    }

    @Test()
    @DisplayName("Get All Employee")
    public void getAllEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmail("test.test@email.com");
        employee.setFirstName("Jay");
        employee.setLastName("Patel");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        ObjectMapper mapper = new ObjectMapper();

        Response responseObj = new Response();
        responseObj.setStatus(HttpStatus.OK);

        when(employeeService.getAllEmployee()).thenReturn(employeeList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/employee/getall")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        Response response = mapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatus());
        Assertions.assertEquals(1, response.getEmployee().size());
        Assertions.assertEquals("Records Found", response.getResponse());
    }


    @Test()
    @DisplayName("Get All Employee without Any records")
    public void getAllEmployeeWithoutRecords() throws Exception {

        List<Employee> employeeList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        Response responseObj = new Response();
        responseObj.setStatus(HttpStatus.OK);

        when(employeeService.getAllEmployee()).thenReturn(employeeList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/employee/getall")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        Response response = mapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatus());
        Assertions.assertEquals(0, response.getEmployee().size());
        Assertions.assertEquals("Unable to find matching records!", response.getResponse());
    }

}
