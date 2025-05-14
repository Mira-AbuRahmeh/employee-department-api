package com.example.employee_And_Department_Management.repositories;

import com.example.employee_And_Department_Management.entities.Department;
import com.example.employee_And_Department_Management.entities.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

    @Modifying
    @Transactional
    @Query("update Department d set d.name=?1 where d.department_Id=?2")
    int updateNameById(String name,int id);

    Optional<Department> findByHead(Employee head);
}
