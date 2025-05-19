package com.myproject.model.dto.employeeDto;

import com.myproject.enums.Role;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class EmployeeUpdateDto {

    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    private Role role;

    @Email(message = "Please provide a valid email address" )
    private String email;

    @DecimalMin(value = "0.0",message = "Salary must be greater than 0")
    private BigDecimal salary;

    private Integer departmentId;

    public EmployeeUpdateDto(String name, Role role, String email, BigDecimal salary, Integer departmentId) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Integer getDepartment() {
        return departmentId;
    }
}
