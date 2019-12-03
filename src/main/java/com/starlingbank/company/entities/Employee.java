package com.starlingbank.company.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Employee {
    private int employeeId;
    private String name;
    private String dateOfBirth;
    private Salary salary;
    protected double bonusPercentage;
    private boolean hasHadAnnualReview;
    private double extraHoursWorked;
    private List<String> coursesEnrolledOn;


    public Employee(int employeeId, String name, String dateOfBirth, Salary salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.bonusPercentage = 0.0;
        this.hasHadAnnualReview = false;
        this.extraHoursWorked = 0.0;
        this.coursesEnrolledOn = new ArrayList<>();
    }

    public int getEmployeeId() { return employeeId; }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public double getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(double bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }

    public boolean hasHadAnnualReview() {
        return hasHadAnnualReview;
    }

    public void setHasHadAnnualReview(boolean hasHadAnnualReview) {
        this.hasHadAnnualReview = hasHadAnnualReview;
    }

    public double getExtraHoursWorked() {
        return extraHoursWorked;
    }

    public void setExtraHoursWorked(double extraHoursWorked) {
        this.extraHoursWorked = extraHoursWorked;
    }

    public List<String> getCoursesEnrolledOn() {
        return coursesEnrolledOn;
    }

    public void setCoursesEnrolledOn(List<String> coursesEnrolledOn) {
        this.coursesEnrolledOn = coursesEnrolledOn;
    }

    @Override
    public String toString() {
        return employeeId + " " + name + ": " + " Born: " + dateOfBirth + " Salary: " + salary + " Bonus of: " + bonusPercentage;
    }


}
