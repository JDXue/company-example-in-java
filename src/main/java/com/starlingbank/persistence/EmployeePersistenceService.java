package com.starlingbank.persistence;

import com.starlingbank.company.entities.Salary;

import java.util.ArrayList;
import java.util.List;

public class EmployeePersistenceService {
    private static int employeeId;
    private String name;
    private String dateOfBirth;
    private Salary salary;
    protected double bonusPercentage;
    private boolean hasHadAnnualReview;
    private double extraHoursWorked;
    private List<String> coursesEnrolledOn;


    public Employee(String name, String dateOfBirth, Salary salary) {
        this.employeeId += 1;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.bonusPercentage = 0.0;
        this.hasHadAnnualReview = false;
        this.extraHoursWorked = 0.0;
        this.coursesEnrolledOn = new ArrayList<>();
    }


    public void addNewManager(){}

    public void addNewProgramme(){}

    public void listEmployees(){}

}
