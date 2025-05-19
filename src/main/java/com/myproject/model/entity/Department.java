package com.myproject.model.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int department_Id;

    private String name;

    private String email;

    @OneToOne
    @JoinColumn(name="head_id",referencedColumnName = "employee_Id",nullable = true)
    private  Employee head;

    @OneToMany(mappedBy="department")
    private List<Employee> employees;


    public int getDepartment_Id() {
        return department_Id;
    }

    public void setDepartment_Id(int department_Id) {
        this.department_Id = department_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee getHead() {
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
