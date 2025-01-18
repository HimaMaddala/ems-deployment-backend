package com.employee_management.management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "holidays")
public class Holiday {
    @Id
    private String holidayId;
    private LocalDate date;
    private String description;
    private String type;

    public String getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(String holidayId) {
        this.holidayId = holidayId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
