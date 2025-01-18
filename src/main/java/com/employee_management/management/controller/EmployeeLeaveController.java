package com.employee_management.management.controller;


import com.employee_management.management.utility.ResponseHelper;
import com.employee_management.management.model.EmployeeLeaves;
import com.employee_management.management.service.EmployeeLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class EmployeeLeaveController {

    @Autowired
    private EmployeeLeaveService employeeLeaveService;

    @PostMapping("/request/{empId}")
    public ResponseEntity<Object> requestEmployeeLeave(@PathVariable String empId, @RequestBody EmployeeLeaves employeeLeaves){
        try{
            EmployeeLeaves data = employeeLeaveService.requestLeave(empId,employeeLeaves);
            return ResponseHelper.createResponse(HttpStatus.OK,"leave requested successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }

    @GetMapping("/approve/{leaveId}")
    public ResponseEntity<Object> ApproveLeave(@PathVariable String leaveId){
        try{
            EmployeeLeaves data = employeeLeaveService.approveLeave(leaveId);
            return ResponseHelper.createResponse(HttpStatus.OK,"leave approved successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }

    @GetMapping("/all/{empId}")
    public ResponseEntity<Object> getAllLeavesByEmpId(@PathVariable String empId){
        try{
            List<EmployeeLeaves> data = employeeLeaveService.getAllLeaveRequests(empId);
            return ResponseHelper.createResponse(HttpStatus.OK,"leave requested successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }

    @GetMapping("/pending/all")
    public ResponseEntity<Object> getPendingApprovals(){
        try{
            List<EmployeeLeaves> data = employeeLeaveService.getPendingApprovals();
            return ResponseHelper.createResponse(HttpStatus.OK,"leave requested successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }

}
