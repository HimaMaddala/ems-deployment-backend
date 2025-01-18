package com.employee_management.management.controller;


import com.employee_management.management.utility.ResponseHelper;
import com.employee_management.management.model.Attendance;
import com.employee_management.management.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark/{id}")
    public ResponseEntity<Object> markAttendance(@PathVariable String id,@RequestBody Attendance attendance){
        try{
            Attendance data =  attendanceService.markAttendance(id,attendance);
            return ResponseHelper.createResponse(HttpStatus.OK,"Attendance retrived successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Object> getAttendanceByEmployee(@PathVariable String employeeId) {
        try{
            List<Attendance> data =  attendanceService.getAttendancesByEmployee(employeeId);
            return ResponseHelper.createResponse(HttpStatus.OK,"data retrived successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }

    @GetMapping("/status/{empId}/{date}")
    public ResponseEntity<Object> getAttendanceByEmployeeAndDate(@PathVariable String empId,@PathVariable String date) {
        try{
            boolean data =  attendanceService.isEmployeeMarkedAttendance(empId,date);
            return ResponseHelper.createResponse(HttpStatus.OK,"data retrived successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<Object> getAttendanceByDate(@PathVariable String date) {
        try{
            List<Attendance> data =  attendanceService.getAttendanceByDate(date);
            return ResponseHelper.createResponse(HttpStatus.OK,"data retrived successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllAttendance() {
        try{
            List<Attendance> data =  attendanceService.getAllAttendance();
            return ResponseHelper.createResponse(HttpStatus.OK,"data retrived successfully",data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(),false,null);
        }
    }
}
