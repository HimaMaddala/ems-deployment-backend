package com.employee_management.management.service;


import com.employee_management.management.model.Department;
import com.employee_management.management.model.Employee;
import com.employee_management.management.repository.DepartmentRepository;
import com.employee_management.management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(String id){
        try{
            return employeeRepository.findByEmpId(id).orElse(null);
        }catch (Exception e){
            throw e;
        }
    }

    public Employee saveEmployee(Employee employee,String departmentId){
       try{
           Optional<Employee> existingEmployee = employeeRepository.findByEmail(employee.getEmail());
           if(existingEmployee.isPresent()){
               throw new RuntimeException("Employee already exists");
           }

           Department department = departmentRepository.findById(departmentId)
                   .orElseThrow(() -> new RuntimeException(("Department not found")));
            employee.setDepartment(department);
           return employeeRepository.save(employee);
       }catch (Exception e){
           throw e;
       }
    }

    public List<String> getAllEmpIds(){
        try{
            List<Employee> employees = employeeRepository.findAll();

            return employees.stream()
                    .map(Employee::getEmpId)
                    .distinct()
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw e;
        }
    }

    public Employee updateEmployee(String id,String departmentId,Employee newEmp){
        try{
            return employeeRepository.findByEmpId(id)
                    .map(employee -> {
                        employee.setName(newEmp.getName());
                        employee.setEmail(newEmp.getEmail());
                        employee.setPhone(newEmp.getPhone());
                        employee.setStatus(newEmp.getStatus());
                        if(departmentId != null){
                            Department department = departmentRepository.findById(departmentId)
                                    .orElseThrow(() -> new RuntimeException("Department not found"));

                            employee.setDepartment(department);
                        }

                        employee.setJobTitle(newEmp.getJobTitle());
                        employee.setDateOfJoining(newEmp.getDateOfJoining());
                        return employeeRepository.save(employee);
                    }).orElseThrow(() -> new RuntimeException("Employee not found"));
        }catch (Exception e){
            throw e;
        }
    }

    public Employee updateEmployeeDetails(String id,Employee newEmp){
        try{
            return employeeRepository.findByEmpId(id)
                    .map(employee -> {
                        employee.setName(newEmp.getName());
                        employee.setEmail(newEmp.getEmail());
                        employee.setPhone(newEmp.getPhone());
                        return employeeRepository.save(employee);
                    }).orElseThrow(() -> new RuntimeException("Employee not found"));
        }catch (Exception e){
            throw e;
        }
    }

    public void deleteEmployee(String id){
       try{
           Optional<Employee> emp = employeeRepository.findByEmpId(id);
           if(emp.isEmpty()){
               throw new RuntimeException("Employee not found");
           }
           employeeRepository.deleteByEmpId(id);
       }catch (Exception e){
           throw e;
       }
    }
}
