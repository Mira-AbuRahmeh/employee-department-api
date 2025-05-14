package com.example.employee_And_Department_Management.services;

import com.example.employee_And_Department_Management.DTO.DepartmentUpdateDTO;
import com.example.employee_And_Department_Management.entities.Department;
import com.example.employee_And_Department_Management.entities.Employee;
import com.example.employee_And_Department_Management.repositories.DepartmentRepo;
import com.example.employee_And_Department_Management.repositories.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServices {


    private final DepartmentRepo DEPARTMENT_REPO;
    private final EmployeeRepo EMPLOYEE_REPO;

    @Autowired
    public DepartmentServices(DepartmentRepo departmentRepo, EmployeeRepo employeeRepo) {
        this.DEPARTMENT_REPO = departmentRepo;
        this.EMPLOYEE_REPO=employeeRepo;
    }

    public void addDepartment(Department department) {
        DEPARTMENT_REPO.save(department);
    }

    public List<Department> getAllDepartments() {
        return DEPARTMENT_REPO.findAll();
    }

    public Department getDepartment(int id) {
        Optional<Department> department=DEPARTMENT_REPO.findById(id);
        if(department.isPresent())
            return  department.get();
        throw new EntityNotFoundException("Department with id "+id+" not found");

    }

    public int  updateName(DepartmentUpdateDTO department, int id) {
        String name= department.getName();
          int row= DEPARTMENT_REPO.updateNameById(name,id);
            if(row==0)
                throw new EntityNotFoundException("Department with id "+id+" not found");
        return row;

    }

    public void deleteDepartment(int id) {
        Optional<Department> department = DEPARTMENT_REPO.findById(id);
        if(department.isPresent()){
            List<Employee> employees=department.get().getEmployees();
            employees.forEach(employee ->{
                employee.setDepartment(null);
                EMPLOYEE_REPO.save(employee);
            });
            DEPARTMENT_REPO.deleteById(id);}
        else
         throw  new EntityNotFoundException("Department with id "+id+" not found");
    }


}


