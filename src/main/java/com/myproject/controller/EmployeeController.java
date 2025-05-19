package com.myproject.controller;

import com.myproject.model.dto.employeeDto.EmployeeAddDto;
import com.myproject.model.dto.employeeDto.EmployeeResponseDto;
import com.myproject.model.dto.employeeDto.EmployeeUpdateDto;
import com.myproject.service.IEmployeeServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("company/employees")
public class EmployeeController {
    public final IEmployeeServices EMPLOYEE_SERVICES;

    @Autowired
    public EmployeeController(IEmployeeServices EMPLOYEE_SERVICES) {
        this.EMPLOYEE_SERVICES = EMPLOYEE_SERVICES;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeAddDto employeeDto){
        EMPLOYEE_SERVICES.addEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully");
    }

    @GetMapping("/view")
    public List<EmployeeResponseDto> getEmployees(@RequestParam(required = false) Integer id){
        if(id != null)
            return List.of(EMPLOYEE_SERVICES.getEmployeeById(id));

        else
            return EMPLOYEE_SERVICES.getAllEmployees();

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        EMPLOYEE_SERVICES.deleteEmployeeById(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeInfo(@Valid @RequestBody EmployeeUpdateDto employeeDto, @PathVariable int id){
        EmployeeResponseDto response = EMPLOYEE_SERVICES.updateEmployee(id,employeeDto);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("search/{keyword}")
    public List<EmployeeResponseDto> searchForEmployee(@PathVariable String keyword){
        return  EMPLOYEE_SERVICES.searchByKeyword(keyword);
    }


    @GetMapping("/filter")
    public List<EmployeeResponseDto> filterEmployees(@RequestParam(required = false)Integer depID, @RequestParam(required = false) BigDecimal minSalary, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String order){
        return EMPLOYEE_SERVICES.filterEmployees(depID,minSalary,sortBy,order);
    }
}
