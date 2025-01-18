package com.employee_management.management.controller;

import com.employee_management.management.model.Holiday;
import com.employee_management.management.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/employee")
    public List<Holiday> getEmployeeHolidays() {
        return holidayService.getAllHolidays();
    }
}
