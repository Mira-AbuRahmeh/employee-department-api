package com.myproject.model.entity;
import com.myproject.enums.Role;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int employee_Id;

    private String name;


    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name="department_id",referencedColumnName = "department_Id")
    private Department department;



    public int getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(int employee_Id){
        this.employee_Id = employee_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
