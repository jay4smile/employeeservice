package com.example.employeeService.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "employee")
public class Employee {



    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "First Name is Mandatory")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last  Name is Mandatory")
    @Column(name = "last_name")
    private String lastName;


    @NotEmpty(message = "Email is Mandatory")
    @Column(name = "email",unique = true)
    private String email;

    @Column(name ="phone")
    private String phone;

}
