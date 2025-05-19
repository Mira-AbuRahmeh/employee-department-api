package com.myproject.model.dto.departmentDto;

import com.myproject.model.dto.employeeDto.EmployeeBasicDto;

import java.util.List;

public class DepartmentResponseDto {

    private String name;

    private String email;

    private EmployeeBasicDto head;


    private List<EmployeeBasicDto> employees;

    public DepartmentResponseDto(String name, String email, EmployeeBasicDto head, List<EmployeeBasicDto> employees) {
        this.name = name;
        this.email = email;
        this.head = head;
        this.employees = employees;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeBasicDto getHead() {
        return head;
    }

    public List<EmployeeBasicDto> getEmployees() {
        return employees;
    }
}
