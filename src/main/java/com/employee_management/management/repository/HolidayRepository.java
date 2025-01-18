package com.employee_management.management.repository;

import com.employee_management.management.model.Holiday;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HolidayRepository extends MongoRepository<Holiday, String> {
}
