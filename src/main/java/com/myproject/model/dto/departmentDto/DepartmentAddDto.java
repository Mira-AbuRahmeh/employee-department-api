package com.myproject.model.dto.departmentDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class DepartmentAddDto {
    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

    private Integer headId;

    public @NotBlank(message = "Name must not be blank") String getName() {
        return name;
    }

    public @NotBlank(message = "Email must not be blank") @Email(message = "Please provide a valid email address") String getEmail() {
        return email;
    }

    public Integer getHeadId() {
        return headId;
    }
}
