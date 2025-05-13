package com.example.employee_And_Department_Management.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@Data
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="employee_id")
    private int employeeId;

    @NotBlank(message = "Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;


    @Enumerated(EnumType.STRING)
    private Role role;


    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;



    @DecimalMin(value = "0.0",message = "Salary must be greater than 0")
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name="department_id",referencedColumnName = "department_Id")
    @JsonIgnoreProperties({"email","head","employees","departmentId"})
    private Department department;

    public @NotBlank(message = "Name must not be blank") @Size(min = 3, message = "Name must be at least 3 characters long") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name must not be blank") @Size(min = 3, message = "Name must be at least 3 characters long") String name) {
        this.name = name;
    }

    public @NotNull Role getRole() {
        return role;
    }

    public void setRole(@NotNull Role role) {
        this.role = role;
    }

    public @NotBlank(message = "Email must not be blank") @Email(message = "Please provide a valid email address") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email must not be blank") @Email(message = "Please provide a valid email address") String email) {
        this.email = email;
    }


    public @NotNull(message = "Salary must not be null") @DecimalMin(value = "0.0", message = "Salary must be greater than 0") BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(@NotNull(message = "Salary must not be null") @DecimalMin(value = "0.0", message = "Salary must be greater than 0") BigDecimal salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public enum Role{
        USER,
        ADMIN,
    }
}
///////////////role