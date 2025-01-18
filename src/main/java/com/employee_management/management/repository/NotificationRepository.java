package com.employee_management.management.repository;

import com.employee_management.management.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification,String> {
    List<Notification> findByEmpId(String empId);

}
