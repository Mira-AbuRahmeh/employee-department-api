package com.example.employee_And_Department_Management.services;


import com.example.employee_And_Department_Management.entities.Department;
import com.example.employee_And_Department_Management.entities.Employee;
import com.example.employee_And_Department_Management.repositories.DepartmentRepo;
import com.example.employee_And_Department_Management.repositories.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServices {

    private final EmployeeRepo EMPLOYEE_REPO;
    private final DepartmentRepo DEPARTMENT_REPO;

    @Autowired
    public EmployeeServices(EmployeeRepo EMPLOYEE_REPO, DepartmentRepo DEPARTMENT_REPO) {
        this.EMPLOYEE_REPO = EMPLOYEE_REPO;
        this.DEPARTMENT_REPO = DEPARTMENT_REPO;
    }

    public void addEmployee(Employee employee) {
        EMPLOYEE_REPO.save(employee);
    }


    public void updateEmployee(int id, Employee emp) {
        Optional<Employee> existingEmployee = EMPLOYEE_REPO.findById(id);
        if(existingEmployee.isPresent()) {
            Employee employee=existingEmployee.get();
            if (emp.getName() != null)
               employee.setName(emp.getName());
            if (emp.getSalary() != null)
                employee.setSalary(emp.getSalary());
            if (emp.getRole() != null)
                employee.setRole(emp.getRole());
            if (emp.getDepartment() != null)
                employee.setDepartment(emp.getDepartment());
            if (emp.getEmail() != null)
                employee.setEmail(emp.getEmail());
            EMPLOYEE_REPO.save(employee);
        }
        else
            throw new EntityNotFoundException("Employee with id " + id + " not found");

    }
    public List<Employee> getAllEmployees() {
        return EMPLOYEE_REPO.findAll();
    }

    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = EMPLOYEE_REPO.findById(id);
        if(employee.isPresent())
            return employee.get();

        throw new EntityNotFoundException("Employee with id " + id + " not found");
    }

    public void deleteEmployeeById(int id) {
        Optional<Employee> employee = EMPLOYEE_REPO.findById(id);
        if(employee.isPresent()){
            Optional<Department> dep= DEPARTMENT_REPO.findByHead(employee.get());
            if (dep.isPresent()){
                dep.get().setHead(null);
                DEPARTMENT_REPO.save(dep.get());
            }
         EMPLOYEE_REPO.deleteById(id);
        }

        else
         throw new EntityNotFoundException("Employee with id " + id + " not found");
    }


    public List<Employee> getEmployeesWithSalaryGreaterThanEqual(BigDecimal salary,Sort sort) {
        return  EMPLOYEE_REPO.findBySalaryGreaterThanEqual(salary,sort);
    }


    public List<Employee> findByDepartmentId(int id,Sort sort) {
        Optional< Department> department = DEPARTMENT_REPO.findById(id);
        if(department.isPresent())
            return EMPLOYEE_REPO.findByDepartment(department.get(),sort);

        throw new EntityNotFoundException("Department with id " + id + " not found");
    }

    public List<Employee> getEmployeesWithSalaryGreaterThanEqualAndDepartment(BigDecimal salary, int departmentId,Sort sort) {
        Optional< Department> department = DEPARTMENT_REPO.findById(departmentId);
        if(department.isPresent())
            return EMPLOYEE_REPO.findBySalaryGreaterThanEqualAndDepartment(salary, department.get(),sort);

        throw new EntityNotFoundException("Department with id " + departmentId + " not found");
    }



    public List<Employee> searchByKeyword(String keyword) {
        return EMPLOYEE_REPO.findByNameOrEmailLike(keyword);
    }

    public List<Employee> filterEmployees(Integer departmentID, BigDecimal minSalary, String sortBy,String order) {
        List<Employee> filteredList=List.of();
        Sort sort=null;
        if(sortBy!=null && !sortBy.isEmpty() ){
            if(order!=null && order.equalsIgnoreCase("asc"))
                sort=Sort.by(sortBy).ascending();
            else
                sort=Sort.by(sortBy).descending();
        }

        if(departmentID != null && minSalary != null ){
            filteredList=this.getEmployeesWithSalaryGreaterThanEqualAndDepartment(minSalary,departmentID,sort);
        }
        else if(departmentID != null){
            filteredList=this.findByDepartmentId(departmentID,sort);
        }
        else if(minSalary != null ){
            filteredList=this.getEmployeesWithSalaryGreaterThanEqual(minSalary,sort);
        }
        else if(sort!=null){
            filteredList=EMPLOYEE_REPO.findAll(sort);
        }
        return filteredList;
    }


}


