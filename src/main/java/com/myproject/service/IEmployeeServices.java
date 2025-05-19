package com.myproject.service;

import com.myproject.exception.ResourceAlreadyExistException;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.model.dto.employeeDto.EmployeeAddDto;
import com.myproject.model.dto.employeeDto.EmployeeResponseDto;
import com.myproject.model.dto.employeeDto.EmployeeUpdateDto;
import com.myproject.model.entity.Department;
import com.myproject.model.entity.Employee;
import com.myproject.util.EmployeeDtoMapper;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IEmployeeServices {
    public void addEmployee(EmployeeAddDto employeeDto) ;

    public EmployeeResponseDto updateEmployee(int id, EmployeeUpdateDto employeeDto);

    public List<EmployeeResponseDto> getAllEmployees() ;

    public EmployeeResponseDto getEmployeeById(int id) ;

    public void deleteEmployeeById(int id) ;

    public List<EmployeeResponseDto> getEmployeesWithSalaryGreaterThanEqual(BigDecimal salary, Sort sort) ;

    public List<EmployeeResponseDto> findByDepartmentId(int id, Sort sort) ;

    public List<EmployeeResponseDto> getEmployeesWithSalaryGreaterThanEqualAndDepartment(BigDecimal salary, int departmentId, Sort sort) ;

    public List<EmployeeResponseDto> searchByKeyword(String keyword) ;

    public List<EmployeeResponseDto> filterEmployees(Integer departmentID, BigDecimal minSalary, String sortBy, String order) ;


}
