package com.myproject.service;

import com.myproject.exception.ResourceAlreadyExistException;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.model.dto.departmentDto.DepartmentAddDto;
import com.myproject.model.dto.departmentDto.DepartmentResponseDto;
import com.myproject.model.dto.departmentDto.DepartmentUpdateDto;
import com.myproject.model.entity.Department;
import com.myproject.model.entity.Employee;
import com.myproject.util.DepartmentDtoMapper;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IDepartmentServices {
    public void addDepartment(DepartmentAddDto departmentDto) ;

    public List<DepartmentResponseDto> getAllDepartments();

    public DepartmentResponseDto getDepartment(int id) ;

    public DepartmentResponseDto updateName(DepartmentUpdateDto departmentDto, int id) ;

    public void deleteDepartment(int id);
}
