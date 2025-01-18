package com.employee_management.management.controller.admin;

import com.employee_management.management.dto.HolidayDTO;
import com.employee_management.management.model.Holiday;
import com.employee_management.management.service.HolidayService;
import com.employee_management.management.utility.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/holidays")
public class AdminHolidayController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHolidayById(@PathVariable String id) {
        try {
            Holiday holiday = holidayService.getHolidayById(id);
            if (holiday == null) {
                return ResponseHelper.createErrorResponse(HttpStatus.NOT_FOUND, "Holiday not found", false, null);
            }
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved holiday", holiday, null);
        } catch (Exception e) {
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }


    @GetMapping
    public ResponseEntity<Object> getAllHolidays() {
        try {
            List<Holiday> holidays = holidayService.getAllHolidays();
            return ResponseHelper.createResponse(HttpStatus.OK, "Successfully retrieved holidays", holidays, null);
        } catch (Exception e) {
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }
@PostMapping
public ResponseEntity<Object> createHoliday(@RequestBody HolidayDTO holidayDTO) {
    try {
        Holiday holiday = holidayService.createHoliday(holidayDTO);
        holidayService.notifyEmployees(holiday);
        return ResponseHelper.createResponse(HttpStatus.CREATED, "Holiday successfully created", holiday, null);
    } catch (Exception e) {
        return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
    }
}

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateHoliday(@PathVariable String id, @RequestBody HolidayDTO holidayDTO) {
        try {
            Holiday updatedHoliday = holidayService.updateHoliday(id, holidayDTO);
            if (updatedHoliday == null) {
                return ResponseHelper.createErrorResponse(HttpStatus.NOT_FOUND, "Holiday not found", false, null);
            }
            return ResponseHelper.createResponse(HttpStatus.OK, "Holiday successfully updated", updatedHoliday, null);
        } catch (Exception e) {
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHoliday(@PathVariable String id) {
        try {
            holidayService.deleteHoliday(id);
            return ResponseHelper.createResponse(HttpStatus.OK, "Holiday successfully deleted", true, null);
        } catch (Exception e) {
            return ResponseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false, null);
        }
    }
}
