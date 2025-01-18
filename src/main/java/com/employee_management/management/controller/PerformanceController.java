package com.employee_management.management.controller;


import com.employee_management.management.dto.PerformanceRequestDto;
import com.employee_management.management.utility.ResponseHelper;
import com.employee_management.management.model.Performance;
import com.employee_management.management.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping("/save")
    public ResponseEntity<Object> sendNotification(@RequestBody PerformanceRequestDto performance) {
        try{
            Performance data = performanceService.savePerformance(performance);
            return ResponseHelper.createResponse(HttpStatus.OK,"Notification created successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<Object> getPerformanceByEmployee(@PathVariable String empId) {
        try{
            List<Performance> data = performanceService.getPerformanceByEmployee(empId);
            return ResponseHelper.createResponse(HttpStatus.OK,"data retrieved successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }

    @GetMapping("/period/{evaluationperiod}")
    public ResponseEntity<Object> getPerformanceByPeriod(@PathVariable String evaluationperiod) {
        try{
            List<Performance> data = performanceService.getPerformanceByPeriod(evaluationperiod);
            return ResponseHelper.createResponse(HttpStatus.OK,"data retrieved successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }
}
