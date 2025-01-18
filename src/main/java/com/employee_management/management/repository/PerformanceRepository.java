package com.employee_management.management.repository;

import com.employee_management.management.model.Employee;
import com.employee_management.management.model.Performance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PerformanceRepository extends MongoRepository<Performance,String> {

    List<Performance> findByEmployee(Employee employee);
    List<Performance> findByEvaluationPeriod(String evaluationPeriod);
}
