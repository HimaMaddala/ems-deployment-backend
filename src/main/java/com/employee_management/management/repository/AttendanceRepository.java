package com.employee_management.management.repository;

import com.employee_management.management.model.Attendance;
import com.employee_management.management.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends MongoRepository<Attendance,String> {

    Attendance findByEmployeeId(Employee employeeId);
    List<Attendance> findByDate(String date);
    List<Attendance> findAllByEmployeeId(Employee employeeId);
    boolean existsByEmployeeIdAndDate(Employee employee, String date);
}
