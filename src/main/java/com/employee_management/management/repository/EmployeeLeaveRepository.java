package com.employee_management.management.repository;

import com.employee_management.management.model.Employee;
import com.employee_management.management.model.EmployeeLeaves;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeLeaveRepository extends MongoRepository<EmployeeLeaves,String> {

    Optional<EmployeeLeaves> findByEmployee(Employee employee);

    List<EmployeeLeaves> findAllByEmployee(Employee employee);
}
