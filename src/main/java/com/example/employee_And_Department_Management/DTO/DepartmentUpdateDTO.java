package com.example.employee_And_Department_Management.DTO;

import jakarta.validation.constraints.NotBlank;

public class DepartmentUpdateDTO {
    @NotBlank(message = "Name must not be blank")
    private String name;

    public @NotBlank(message = "Name must not be blank") String getName() {
        return name;
    }
}
