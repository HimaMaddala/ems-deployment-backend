package com.employee_management.management.controller.admin;

import com.employee_management.management.utility.ResponseHelper;
import com.employee_management.management.model.Attendance;
import com.employee_management.management.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin/attendance")
public class AdminAttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<Object> getAllAttendance(){
        try{
            List<Attendance> data = attendanceService.getAllAttendance();
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @GetMapping("/{date}")
    public ResponseEntity<Object> getAttendanceByDate(@PathVariable String date){
        try{
            List<Attendance> data = attendanceService.getAttendanceByDate(date);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Object> getAttendanceByEmpId(@PathVariable String empId){
        try{
            Attendance data = attendanceService.getAttendanceByEmployee(empId);
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved", data,null);
        }catch (Exception e){
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),false,null);
        }
    }
}
