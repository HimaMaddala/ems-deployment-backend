package com.employee_management.management.service;

import com.employee_management.management.model.Attendance;
import com.employee_management.management.model.Employee;
import com.employee_management.management.repository.AttendanceRepository;
import com.employee_management.management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Attendance markAttendance(String id, Attendance attendance) {
        try {
            attendance.setTimestamp(LocalDateTime.now());
            Employee employee = employeeRepository.findByEmpId(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            String todayDate = attendance.getDate();
            boolean isAttendanceAlreadyMarked = attendanceRepository.existsByEmployeeIdAndDate(employee, todayDate);

            if (isAttendanceAlreadyMarked) {
                throw new RuntimeException("Attendance already marked for today");
            }

            attendance.setEmployeeId(employee);
            return attendanceRepository.save(attendance);
        } catch (Exception e) {
            throw e;
        }
    }



    public List<Attendance> getAttendancesByEmployee(String employeeId) {
        Employee employee = employeeRepository.findByEmpId(employeeId).orElseThrow(() -> new RuntimeException("employee not found"));
        return attendanceRepository.findAllByEmployeeId(employee);
    }

    public Attendance getAttendanceByEmployee(String employeeId) {
        Employee employee = employeeRepository.findByEmpId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Attendance attendance = attendanceRepository.findByEmployeeId(employee);

        if (attendance == null) {
            throw new RuntimeException("Attendance record not found for employee");
        }

        return attendance;
    }


    public boolean isEmployeeMarkedAttendance(String employeeId, String date) {
        Employee employee = employeeRepository.findByEmpId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Attendance attendance = attendanceRepository.findByEmployeeId(employee);

        if (attendance == null) {
            throw new RuntimeException("Attendance record not found for employee");
        }

        String updatedDate = attendance.getDate();
        return updatedDate.equals(date);
    }


    public List<Attendance> getAttendanceByDate(String date) {
        return attendanceRepository.findByDate(date);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
}
