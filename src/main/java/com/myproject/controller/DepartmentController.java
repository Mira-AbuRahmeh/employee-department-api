package com.myproject.controller;
import com.myproject.model.dto.departmentDto.DepartmentAddDto;
import com.myproject.model.dto.departmentDto.DepartmentResponseDto;
import com.myproject.model.dto.departmentDto.DepartmentUpdateDto;
import com.myproject.service.IDepartmentServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company/departments")
public class DepartmentController {

    public final IDepartmentServices DEPARTMENT_SERVICES;

    @Autowired
    public DepartmentController(IDepartmentServices DEPARTMENT_SERVICES) {
        this.DEPARTMENT_SERVICES = DEPARTMENT_SERVICES;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewDepartment(@Valid @RequestBody DepartmentAddDto departmentDto){
        DEPARTMENT_SERVICES.addDepartment(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Department added successfully");

    }


    @GetMapping("/view")
    public List<DepartmentResponseDto> getDepartments(@RequestParam(required = false) Integer id){
        if(id != null)
           return List.of(DEPARTMENT_SERVICES.getDepartment(id));

        else
            return DEPARTMENT_SERVICES.getAllDepartments();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id){
        DEPARTMENT_SERVICES.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.OK).body("Department with id "+id+" deleted successfully");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<DepartmentResponseDto> updateDepartmentName(@PathVariable(required = true) int id, @Valid @RequestBody DepartmentUpdateDto department){
       DepartmentResponseDto response= DEPARTMENT_SERVICES.updateName(department,id);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}


