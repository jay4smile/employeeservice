package com.example.employeeService.repository;

import com.example.employeeService.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
    List<Employee> findByEmail(String email);


}
