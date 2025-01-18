package com.employee_management.management.controller;

import com.employee_management.management.utility.ResponseHelper;
import com.employee_management.management.model.Employee;
import com.employee_management.management.model.User;
import com.employee_management.management.service.AuthService;
import com.employee_management.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/employees")
public class AppEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<Object> getAllEmployees(){
        try{
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", employees,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @GetMapping("/admin/ids")
    public ResponseEntity<Object> getAdminId(){
        try{
            User data = authService.getAdminId();
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAllEmployees(@PathVariable String id){
        try{
            Employee emp = employeeService.getEmployeeById(id);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", emp,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateEmployeeDetails(@PathVariable String id,@RequestBody Employee employee){
        try{
            Employee emp = employeeService.updateEmployeeDetails(id,employee);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", emp,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

}
