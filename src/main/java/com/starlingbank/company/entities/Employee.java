package com.starlingbank.company.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Employee {
    private int employeeId;
    private String name;
    private String dateOfBirth;
    private Salary salary;
    protected double bonusPercentage;
    private boolean hasHadAnnualMeeting;
    private double extraHoursWorked;
    private List<String> coursesEnrolledOn;


    public Employee(String name, String dateOfBirth, Salary salary) {
//        this.employeeId =
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.bonusPercentage = 0.0;
        this.hasHadAnnualMeeting = false;
        this.extraHoursWorked = 0.0;
        this.coursesEnrolledOn = new ArrayList<>();
    }

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
        return hasHadAnnualMeeting;
    }

    public void setHasHadAnnualMeeting(boolean hasHadAnnualMeeting) {
        this.hasHadAnnualMeeting = hasHadAnnualMeeting;
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
        return name + ": " + " Born: " + dateOfBirth + " Salary: " + salary + " Bonus of: " + bonusPercentage;
    }


}
