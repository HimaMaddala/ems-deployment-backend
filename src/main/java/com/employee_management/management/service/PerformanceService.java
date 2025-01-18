package com.employee_management.management.service;


import com.employee_management.management.dto.PerformanceRequestDto;
import com.employee_management.management.model.Employee;
import com.employee_management.management.model.Performance;
import com.employee_management.management.repository.EmployeeRepository;
import com.employee_management.management.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Performance savePerformance(PerformanceRequestDto body) {
        Employee emp = employeeRepository.findByEmpId(body.getEmployee()).orElseThrow(() -> new RuntimeException("Employee not found"));
        Performance performance = new Performance();
        performance.setEmployee(emp);
        performance.setEvaluator(body.getEvaluator());
        performance.setRating(body.getRating());
        performance.setRemarks(body.getRemarks());
        performance.setEvaluationPeriod(body.getEvaluationPeriod());

        return performanceRepository.save(performance);
    }

    public List<Performance> getPerformanceByEmployee(String employeeId) {
        Employee employee = employeeRepository.findByEmpId(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        return performanceRepository.findByEmployee(employee);
    }

    public List<Performance> getPerformanceByPeriod(String evaluationPeriod) {
        return performanceRepository.findByEvaluationPeriod(evaluationPeriod);
    }
}
