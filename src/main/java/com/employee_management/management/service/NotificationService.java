package com.employee_management.management.service;

import com.employee_management.management.model.Employee;
import com.employee_management.management.model.Holiday;
import com.employee_management.management.model.Notification;
import com.employee_management.management.repository.EmployeeRepository;
import com.employee_management.management.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

public List<Notification> getNotificationForEmployee(String empId) {
    List<Notification> notifications = notificationRepository.findByEmpId(empId);
    System.out.println("Notifications retrieved for empId " + empId + ": " + notifications);
    return notifications;
}

    public void markAsRead(String notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("notification not found"));
        notification.setReadStatus(true);
        notificationRepository.save(notification);
    }

    public void markAllAsRead(String empId) {
        List<Notification> notifications = notificationRepository.findByEmpId(empId);
        notifications.forEach(notification -> {
            notification.setReadStatus(true);
        });
        notificationRepository.saveAll(notifications);
    }

    public void notifyEmployees(Holiday holiday) {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            Notification notification = new Notification();
            notification.setEmpId(employee.getEmployeeId());
            notification.setMessage("New Holiday: " + holiday.getDescription() + " on " + holiday.getDate());
            notification.setType("ANNOUNCEMENT");
            notification.setDateSent(LocalDate.now().toString());
            notification.setReadStatus(false);
            notificationRepository.save(notification);
        }
    }
}
