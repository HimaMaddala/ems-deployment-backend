package com.employee_management.management.dto;

public class PerformanceRequestDto {

    private String employee;
    private String evaluationPeriod;
    private String evaluator;
    private int rating;
    private String remarks;

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getEvaluationPeriod() {
        return evaluationPeriod;
    }

    public void setEvaluationPeriod(String evaluationPeriod) {
        this.evaluationPeriod = evaluationPeriod;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
