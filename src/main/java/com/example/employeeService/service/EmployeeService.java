package com.example.employeeService.service;


import com.example.employeeService.model.Employee;
import com.example.employeeService.model.Response;
import com.example.employeeService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/***
 * Service to handle Employee operations
 *
 * developed by Jay
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    /***
     * Method to save employee details
     *
     * @param employee
     * @return Response
     */
    public Response saveEmployee(Employee employee) {

        Response responseObj = new Response();
        List<Employee> emp = employeeRepository.findByEmail(employee.getEmail());
        /*
        * Check whether employee with same email is present or not
        * */
        if (CollectionUtils.isEmpty(emp)) {
             employeeRepository.save(employee);
             responseObj.setResponse("Employee details saved successfully!");
             responseObj.setStatus(HttpStatus.OK);
        } else {
            responseObj.setResponse("Employee details already exists");
            responseObj.setStatus(HttpStatus.BAD_REQUEST);
        }
        return responseObj;
    }

    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    /***
     * Method to update the employee
     *
     * @param emp
     * @return Response
     */
    public Response updateEmployee(Employee emp) {
        Response response = new Response();
        List<Employee> employeeByEmail = employeeRepository.findByEmail( emp.getEmail());
        /*
        * Employee ID must passed from request
        * */
        if (null != emp.getId()) {
            Optional<Employee> empById = employeeRepository.findById(emp.getId());

            if (empById.isPresent()) {
                if (!checkEmployeeList(employeeByEmail, emp.getId())) {
                    employeeRepository.save(emp);
                    response.setStatus(HttpStatus.OK);
                    response.setResponse("Employee details updated successfully!");
                } else {
                    response.setStatus(HttpStatus.BAD_GATEWAY);
                    response.setResponse("Email details already exist!");
                }

            } else {
                response.setStatus(HttpStatus.BAD_GATEWAY);
                response.setResponse("Employee record does not exist!");
            }


        } else {
            response.setStatus(HttpStatus.BAD_GATEWAY);
            response.setResponse("Unable to update Employee record!");
        }

        return response;
    }

    private boolean checkEmployeeList(List<Employee> employeeList, int id) {
        boolean isExist = false;
        if (!CollectionUtils.isEmpty(employeeList) ) {
            Optional<Employee> optionalEmployee =  employeeList.stream()
                    .filter(employee ->  employee.getId() != id)
                    .findAny();

            if (optionalEmployee.isPresent()) {
                isExist = true;
            }
        }
        return isExist;
    }

}
