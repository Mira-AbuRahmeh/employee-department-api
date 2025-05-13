package com.example.employee_And_Department_Management.repositories;

import com.example.employee_And_Department_Management.entities.Department;
import com.example.employee_And_Department_Management.entities.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {


    List<Employee> findBySalaryGreaterThanEqual(BigDecimal salary, Sort sort);

    List<Employee> findByDepartment(Department department,Sort sort);

    @Query("Select e from Employee e where LOWER(e.name) like lower(CONCAT('%', ?1, '%')) or lower(e.email) like lower(CONCAT('%', ?1, '%'))")
    List<Employee> findByNameOrEmail(String keyword);

    List<Employee> findBySalaryGreaterThanEqualAndDepartment(BigDecimal salary, Department department,Sort sort);





}
