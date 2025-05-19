package com.myproject.model.dto.employeeDto;

import com.myproject.enums.Role;
import com.myproject.model.dto.departmentDto.DepartmentBasicDto;

import java.math.BigDecimal;

public class EmployeeResponseDto {

    private String name;
    private Role role;
    private String email;
    private BigDecimal salary;
    private DepartmentBasicDto department;

    public EmployeeResponseDto(String name, Role role, String email, BigDecimal salary, DepartmentBasicDto department) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.salary = salary;
        this.department = department;
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

    public DepartmentBasicDto getDepartment() {
        return department;
    }
}
