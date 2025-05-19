package com.myproject.model.dto.departmentDto;

import jakarta.validation.constraints.NotBlank;

public class DepartmentUpdateDto {

    @NotBlank(message = "Name must not be blank")
    private String name;

    public @NotBlank(message = "Name must not be blank") String getName() {
        return name;
    }
}
