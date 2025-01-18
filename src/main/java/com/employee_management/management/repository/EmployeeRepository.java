package com.employee_management.management.repository;

import com.employee_management.management.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee,String> {

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByEmpId (String empId);

    void deleteByEmpId(String empId);

}
