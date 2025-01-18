package com.employee_management.management.service;

import com.employee_management.management.model.Employee;
import com.employee_management.management.model.EmployeeLeaves;
import com.employee_management.management.repository.EmployeeLeaveRepository;
import com.employee_management.management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeLeaveService {

    @Autowired
    private EmployeeLeaveRepository employeeLeaveRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeLeaves requestLeave(String empId,EmployeeLeaves employeeLeaves){
        Employee employee = employeeRepository.findByEmpId(empId)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
        employeeLeaves.setEmployee(employee);
        return employeeLeaveRepository.save(employeeLeaves);
    }

    public EmployeeLeaves approveLeave(String leaveId){
        try{
            EmployeeLeaves empLeave = employeeLeaveRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Employee leave not found"));
            empLeave.setApproved(true);
            empLeave.setApprovalDate(new Date().toString());
            return employeeLeaveRepository.save(empLeave);
        }catch (Exception e){
            throw e;
        }
    }

    public List<EmployeeLeaves> getAllLeaveRequests(String empId){
        Employee employee = employeeRepository.findByEmpId(empId)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
        List<EmployeeLeaves> empLeaves = employeeLeaveRepository.findAllByEmployee(employee);
        return empLeaves;
    }

    public List<EmployeeLeaves> getPendingApprovals(){
        List<EmployeeLeaves> empLeaves = employeeLeaveRepository.findAll()
                .stream()
                .filter(empLeave -> empLeave.isApproved() == false)
                .collect(Collectors.toList());
        return empLeaves;
    }

    public EmployeeLeaves getLeaveRequests(String empId){
        Employee employee = employeeRepository.findByEmpId(empId)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
        EmployeeLeaves empLeaves = employeeLeaveRepository.findByEmployee(employee)
                .orElseThrow(() -> new RuntimeException("Employee leave not found"));
        return empLeaves;
    }
}
