package com.employee_management.management.service;

import com.employee_management.management.model.Department;
import com.employee_management.management.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        try{
            return departmentRepository.findAll();
        }catch (Exception e){
            throw e;
        }
    }

    public Department saveDepartment(Department department) {
        try {
            Optional<Department> existingDept = departmentRepository.findByName(department.getName());
            if (existingDept.isPresent()) {
                throw new RuntimeException("Department with name already taken");
            }
            department.setCreatedDate(LocalDate.now());
            return departmentRepository.save(department);
        } catch (Exception e) {
            throw e;
        }
    }


    public Optional<Department> getDepartmentById(String id){
        try{
            return departmentRepository.findById(id);
        }catch (Exception e){
            throw e;
        }
    }

    public Department updateDepartment(String id,Department newDept){
        try{
            return departmentRepository.findById(id)
                    .map(department -> {
                        department.setName(newDept.getName());
                        department.setDescription(newDept.getDescription());
                        return departmentRepository.save(department);
                    }).orElseThrow(() -> new RuntimeException("Department not found"));
        }catch (Exception e){
            throw e;
        }
    }

    public void deleteDepartment(String id){
        try{
            departmentRepository.deleteById(id);
        }catch (Exception e){
            throw e;
        }
    }
}
