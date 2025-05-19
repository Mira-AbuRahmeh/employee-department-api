package com.myproject.repository;

import com.myproject.model.entity.Department;
import com.myproject.model.entity.Employee;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    Optional<Department> findByName(String name);

    Optional<Department> findByEmail(@NotBlank(message = "Email must not be blank") @Email(message = "Please provide a valid email address") String email);
}
