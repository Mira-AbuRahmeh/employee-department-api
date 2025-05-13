package com.example.employee_And_Department_Management.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


import java.util.List;

@Entity
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "department_id")
    private int departmentId;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

    @OneToOne
    @JoinColumn(name="head_id",referencedColumnName = "employee_Id",nullable = true)
    @JsonIgnoreProperties({"employee_id","role","salary","department"})
    private  Employee head;

    @OneToMany(mappedBy="department")
    private List<Employee> employees;

    public @NotBlank(message = "Name must not be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name must not be blank") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email must not be blank") @Email(message = "Please provide a valid email address") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email must not be blank") @Email(message = "Please provide a valid email address") String email) {
        this.email = email;
    }

    public Employee getHead() {
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }
}
