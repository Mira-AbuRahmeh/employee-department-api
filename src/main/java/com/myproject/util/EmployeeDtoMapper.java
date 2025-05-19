package com.myproject.util;
import com.myproject.model.dto.employeeDto.EmployeeAddDto;
import com.myproject.model.dto.employeeDto.EmployeeBasicDto;
import com.myproject.model.dto.employeeDto.EmployeeResponseDto;
import com.myproject.model.entity.Department;
import com.myproject.model.entity.Employee;



public class EmployeeDtoMapper {


    public static EmployeeResponseDto toEmployeeResponseDto(Employee employee) {
        return new EmployeeResponseDto(employee.getName(),
                employee.getRole(),
                employee.getEmail(),
                employee.getSalary(),
                employee.getDepartment() != null ? DepartmentDtoMapper.toDepartmentBasicDto(employee.getDepartment()) : null);
    }



    public static EmployeeBasicDto toEmployeeBasicDto(Employee employee) {
            return new EmployeeBasicDto(employee.getEmployee_Id(), employee.getName(), employee.getEmail());
    }




    public static Employee fromEmployeeAddDto(EmployeeAddDto employeeAddDto, Department department) {
        Employee employee = new Employee();
        employee.setName(employeeAddDto.getName());
        employee.setRole(employeeAddDto.getRole());
        employee.setEmail(employeeAddDto.getEmail());
        employee.setSalary(employeeAddDto.getSalary());
        employee.setDepartment(department);

        return employee;
    }

}