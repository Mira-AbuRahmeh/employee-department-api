package com.myproject.service.impl;

import com.myproject.model.dto.departmentDto.DepartmentAddDto;
import com.myproject.model.dto.departmentDto.DepartmentResponseDto;
import com.myproject.model.dto.departmentDto.DepartmentUpdateDto;
import com.myproject.model.entity.Department;
import com.myproject.model.entity.Employee;
import com.myproject.exception.ResourceAlreadyExistException;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.repository.DepartmentRepo;
import com.myproject.repository.EmployeeRepo;
import com.myproject.service.IDepartmentServices;
import com.myproject.util.DepartmentDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentServicesImpl implements IDepartmentServices {

    private final DepartmentRepo DEPARTMENT_REPO;
    private final EmployeeRepo EMPLOYEE_REPO;

    @Autowired
    public DepartmentServicesImpl(DepartmentRepo departmentRepo, EmployeeRepo employeeRepo) {
        this.DEPARTMENT_REPO = departmentRepo;
        this.EMPLOYEE_REPO = employeeRepo;
    }
 @Override
    public void addDepartment(DepartmentAddDto departmentDto) {
        Optional<Department> departmentWithName= DEPARTMENT_REPO.findByName(departmentDto.getName());
        Optional<Department> departmentWithEmail= DEPARTMENT_REPO.findByEmail(departmentDto.getEmail());
        Optional<Employee> head=Optional.empty();

        if(departmentWithName.isPresent())
            throw  new ResourceAlreadyExistException("Department","Name",departmentDto.getName());
        if (departmentWithEmail.isPresent())
            throw  new ResourceAlreadyExistException("Department","Email",departmentDto.getEmail());
        if(departmentDto.getHeadId()!=null){
            head= EMPLOYEE_REPO.findById(departmentDto.getHeadId());
            if(head.isPresent()){
                Optional<Department> departmentWithHead=DEPARTMENT_REPO.findByHead(head.get());
                if(departmentWithHead.isPresent())
                    throw new ResourceAlreadyExistException("Department","Head",String.valueOf(head.get().getEmployee_Id()));
            }
            else {
                throw new ResourceNotFoundException("Employee","id",String.valueOf(departmentDto.getHeadId()));
            }
        }


         DEPARTMENT_REPO.save(DepartmentDtoMapper.fromDepartmentAddDto(departmentDto,head.orElse(null)));
    }
    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return DEPARTMENT_REPO.findAll().stream().map(DepartmentDtoMapper::toDepartmentResponseDto).toList();
    }

    @Override
    public DepartmentResponseDto getDepartment(int id) {
        Optional<Department> department=DEPARTMENT_REPO.findById(id);
        if(department.isPresent()){
            return  DepartmentDtoMapper.toDepartmentResponseDto(department.get());
        }

        throw new ResourceNotFoundException("Department","id",String.valueOf(id));

    }
    @Override
    public DepartmentResponseDto updateName(DepartmentUpdateDto departmentDto, int id) {

        Optional<Department> department=DEPARTMENT_REPO.findById(id);
        String name=departmentDto.getName();
        if(department.isPresent()) {
            Optional<Department> dep = DEPARTMENT_REPO.findByName(name);
            if (dep.isPresent()) {
                throw new ResourceAlreadyExistException("Department", "name", name);
            } else {
                department.get().setName(name);
                return DepartmentDtoMapper.toDepartmentResponseDto(DEPARTMENT_REPO.save(department.get()));
            }
        }
        else
          throw new EntityNotFoundException("Department with id "+id+" not found");

    }
    @Override
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
         throw  new ResourceNotFoundException("Department","id",String.valueOf(id));
    }


}


