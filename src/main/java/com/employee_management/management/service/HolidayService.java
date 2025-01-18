package com.employee_management.management.service;

import com.employee_management.management.dto.HolidayDTO;
import com.employee_management.management.model.Employee;
import com.employee_management.management.model.Holiday;
import com.employee_management.management.model.Notification;
import com.employee_management.management.repository.EmployeeRepository;
import com.employee_management.management.repository.HolidayRepository;
import com.employee_management.management.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Holiday> getAllHolidays() {
        return holidayRepository.findAll();
    }

    public Holiday createHoliday(HolidayDTO holidayDTO) {
        Holiday holiday = new Holiday();
        holiday.setDate(holidayDTO.getDate());
        holiday.setDescription(holidayDTO.getDescription());
        holiday.setType(holidayDTO.getType());
        return holidayRepository.save(holiday);
    }

    public void deleteHoliday(String holidayId) {
        holidayRepository.deleteById(holidayId);
    }

    public Holiday getHolidayById(String id) {
        return holidayRepository.findById(id).orElse(null);
    }
    public Holiday updateHoliday(String id, HolidayDTO holidayDTO) {
        Optional<Holiday> optionalHoliday = holidayRepository.findById(id);
        if (!optionalHoliday.isPresent()) {
            return null;
        }
        Holiday holiday = optionalHoliday.get();
        if (holidayDTO.getDate() != null) holiday.setDate(holidayDTO.getDate());
        if (holidayDTO.getDescription() != null) holiday.setDescription(holidayDTO.getDescription());
        if (holidayDTO.getType() != null) holiday.setType(holidayDTO.getType());
        return holidayRepository.save(holiday);
    }



//    public void notifyEmployees(Holiday holiday) {
//        List<Employee> employees = employeeRepository.findAll();
//        for (Employee employee : employees) {
//            Notification notification = new Notification();
//            notification.setEmpId(employee.getEmployeeId());
//            notification.setMessage("New Holiday Announced: " + holiday.getDescription() + " on " + holiday.getDate());
//            notification.setType("announcement");
//            notification.setDateSent(LocalDate.now().toString());
//            notification.setReadStatus(false);
//            notificationRepository.save(notification);
//        }
//    }
public void notifyEmployees(Holiday holiday) {
    List<Employee> employees = employeeRepository.findAll();
    for (Employee employee : employees) {
        Notification notification = new Notification();
        notification.setEmpId(employee.getEmployeeId());  // Ensure correct `empId` assignment
        notification.setMessage("New Holiday Announced: " + holiday.getDescription() + " on " + holiday.getDate());
        notification.setType("ANNOUNCEMENT");
        notification.setDateSent(LocalDate.now().toString());
        notification.setReadStatus(false);
        notificationRepository.save(notification);

        System.out.println("Notification created for: " + employee.getEmployeeId());  // Debug log
    }
}

}
