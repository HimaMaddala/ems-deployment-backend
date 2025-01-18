package com.employee_management.management.controller.admin;
import java.util.*;

import com.employee_management.management.utility.ResponseHelper;
import com.employee_management.management.model.Employee;
import com.employee_management.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/employees")
//@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public ResponseEntity<Object> getAllEmployees(){
        try{
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", employees,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable String id){
        try{
            Employee employee = employeeService.getEmployeeById(id);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", employee,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @PostMapping("/{departmentId}")
    public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee,@PathVariable String departmentId){
        try{
            Employee empData = employeeService.saveEmployee(employee,departmentId);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", empData,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @GetMapping("/empIds")
    public ResponseEntity<Object> getAllEmpIds(){
        try{
            List<String> empData = employeeService.getAllEmpIds();
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", empData,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @PatchMapping("/{id}/{departmentId}")
    public ResponseEntity<Object> updateEmployee(@PathVariable String id,@PathVariable String departmentId,@RequestBody Employee employee){
        try{
            Employee empData = employeeService.updateEmployee(id,departmentId,employee);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", empData,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String id){
        try{
            employeeService.deleteEmployee(id);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", true,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }
}
