package com.employee_management.management.controller.admin;

import com.employee_management.management.utility.ResponseHelper;
import com.employee_management.management.model.Department;
import com.employee_management.management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/departments")
public class AdminDepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<Object> getAllDepartments() {
        try{
            List<Department> dept = departmentService.getAllDepartments();
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", dept,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDepartmentById(@PathVariable String id) {
        try{
            Optional<Department> dept = departmentService.getDepartmentById(id);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", dept,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveDepartment(@RequestBody Department department){
        try{
            Department deptData = departmentService.saveDepartment(department);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", deptData,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateDepartment(@PathVariable String id,@RequestBody Department department){
        try{
            Department dptData = departmentService.updateDepartment(id,department);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved",dptData,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String id){
        try{
            departmentService.deleteDepartment(id);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", true,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

}
