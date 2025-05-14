package com.example.employee_And_Department_Management.controllers;

import com.example.employee_And_Department_Management.entities.Employee;
import com.example.employee_And_Department_Management.entities.Response;
import com.example.employee_And_Department_Management.services.EmployeeServices;
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
    public final EmployeeServices EMPLOYEE_SERVICES;

    @Autowired
    public EmployeeController(EmployeeServices EMPLOYEE_SERVICES) {
        this.EMPLOYEE_SERVICES = EMPLOYEE_SERVICES;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addEmployee(@Valid @RequestBody Employee employee){
        EMPLOYEE_SERVICES.addEmployee(employee);
        Response response=new Response();
        response.setStatusCode("201");
        response.setStatusMsg("Employee added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/viewAll")
    public List<Employee> viewAllEmployees(){
        return EMPLOYEE_SERVICES.getAllEmployees();
    }

    @GetMapping("/view/{id}")
    public Employee getEmployee(@PathVariable int id){
        return EMPLOYEE_SERVICES.getEmployeeById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable int id){
        EMPLOYEE_SERVICES.deleteEmployeeById(id);
        Response response=new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Employee deleted successfully");
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Response> updateEmployeeInfo(@RequestBody Employee employee,@PathVariable int id){
        EMPLOYEE_SERVICES.updateEmployee(id,employee);
        Response response=new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Employee info updated successfully");
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("search/{keyword}")
    public List<Employee> searchForEmployee(@PathVariable String keyword){
        return  EMPLOYEE_SERVICES.searchByKeyword(keyword);
    }


    @GetMapping("/filter")
    public List<Employee> filterEmployees(@RequestParam(required = false)Integer depID, @RequestParam(required = false) BigDecimal minSalary, @RequestParam(required = false) String sortBy,@RequestParam(required = false) String order){
        return EMPLOYEE_SERVICES.filterEmployees(depID,minSalary,sortBy,order);
    }
}
