package com.starlingbank.company.entities;

public abstract class Employee {
    private int employeeId;
    private String name;
    private String dateOfBirth;

    private int salaryId; //salary id made available for using database employee service
    private Salary salary;

    protected double bonusPercentage;
    private boolean hasHadAnnualReview;
    private double extraHoursWorked;

    public Employee(int employeeId, String name, String dateOfBirth, Salary salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.bonusPercentage = 0.0;
        this.hasHadAnnualReview = false;
        this.extraHoursWorked = 0.0;
    }

    public Employee(int employeeId, String name, String dateOfBirth, int salaryId) {
        this.employeeId = employeeId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salaryId = salaryId;
        this.bonusPercentage = 0.0;
        this.hasHadAnnualReview = false;
        this.extraHoursWorked = 0.0;
    }

    public int getEmployeeId() { return employeeId; }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public Salary getSalaryObject(){
        return salary;
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


    @Override
    public String toString() {
        return employeeId + " " + name + ": " + " Born: " + dateOfBirth + " Salary: " + salaryId + " Bonus of: " + bonusPercentage;
    }


}
