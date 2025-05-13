package com.example.employee_And_Department_Management.controllers;
import com.example.employee_And_Department_Management.DTO.DepartmentUpdateDTO;
import com.example.employee_And_Department_Management.entities.Department;
import com.example.employee_And_Department_Management.entities.Response;
import com.example.employee_And_Department_Management.services.DepartmentServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company/departments")
public class DepartmentController {
    public final DepartmentServices DEPARTMENT_SERVICES;

    @Autowired
    public DepartmentController(DepartmentServices DEPARTMENT_SERVICES) {
        this.DEPARTMENT_SERVICES = DEPARTMENT_SERVICES;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addNewDepartment(@Valid @RequestBody Department department){
        DEPARTMENT_SERVICES.addDepartment(department);
        Response response=new Response();
        response.setStatusCode("201");
        response.setStatusMsg("Department added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/viewAll")
    public List<Department> viewAllDepartments(){
        return DEPARTMENT_SERVICES.getAllDepartments();
    }

    @GetMapping("/view/{id}")
    public Department getDepartment(@PathVariable(required = true) int id){
        return DEPARTMENT_SERVICES.getDepartment(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteDepartment(@PathVariable(required = true) int id){
        DEPARTMENT_SERVICES.deleteDepartment(id);
        Response response=new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Department deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PatchMapping("/updateName/{id}")
    public ResponseEntity<Response> updateDepartmentName(@PathVariable(required = true) int id, @Valid @RequestBody DepartmentUpdateDTO department){
        DEPARTMENT_SERVICES.updateName(department,id);
        Response response=new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Department name updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}


