package com.employee_management.management.repository;

import com.employee_management.management.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DepartmentRepository extends MongoRepository<Department,String> {

    Optional<Department> findByName(String name);
}
