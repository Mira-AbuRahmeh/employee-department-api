package com.myproject.model.dto.employeeDto;

import com.myproject.enums.Role;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class EmployeeAddDto {
    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotNull(message = "Role must not be null")
    private Role role;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @DecimalMin(value = "0.0", message = "Salary must be greater than 0")
    private BigDecimal salary;

    @NotNull(message = "Department ID must not be null")
    private Integer departmentId;

    public EmployeeAddDto(String name, Role role, String email, BigDecimal salary, Integer departmentId) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public @NotBlank(message = "Name must not be blank") String getName() {
        return name;
    }

    public @NotNull(message = "Role must not be null") Role getRole() {
        return role;
    }

    public @Email(message = "Please provide a valid email address") @NotBlank(message = "Email must not be blank") String getEmail() {
        return email;
    }

    public @DecimalMin(value = "0.0", message = "Salary must be greater than 0") BigDecimal getSalary() {
        return salary;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }
}
