package com.example.employee_And_Department_Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.employee_And_Department_Management.repositories")
@EntityScan("com.example.employee_And_Department_Management.entities")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class EmployeeAndDepartmentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAndDepartmentManagementApplication.class, args);
	}

}
