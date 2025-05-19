package com.myproject.util;
import com.myproject.model.dto.departmentDto.DepartmentAddDto;
import com.myproject.model.dto.departmentDto.DepartmentBasicDto;
import com.myproject.model.dto.departmentDto.DepartmentResponseDto;
import com.myproject.model.entity.Department;
import com.myproject.model.entity.Employee;

public class DepartmentDtoMapper {


    public static DepartmentResponseDto toDepartmentResponseDto(Department department) {
        return new DepartmentResponseDto(department.getName(),
                department.getEmail(),
                department.getHead() != null ? EmployeeDtoMapper.toEmployeeBasicDto(department.getHead()):null,
                department.getEmployees().stream().map(EmployeeDtoMapper::toEmployeeBasicDto).toList());
    }

    public static DepartmentBasicDto toDepartmentBasicDto(Department department) {

            return new DepartmentBasicDto(department.getDepartment_Id(), department.getName(), department.getEmail());

    }


    public static Department fromDepartmentAddDto(DepartmentAddDto departmentAddDto, Employee employee) {
        Department department = new Department();
        department.setName(departmentAddDto.getName());
        department.setEmail(departmentAddDto.getEmail());
        department.setHead(employee);

        return department;
    }
}
