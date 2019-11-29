package com.starlingbank.company.entities;

import java.util.ArrayList;

public class Manager extends Employee {
    private ArrayList<Employee> employeesManaging; //always want the manager to manage no employees at the beginning

    public Manager(String name, String dateOfBirth, Salary salary){
        super(name, dateOfBirth, salary);
        bonusPercentage = 0.3;
        employeesManaging = new ArrayList<>();
    }


    public void manageProgram(){
        System.out.println("I am managing a cool application");
    }


    public void addNewEmployeeToManage(Employee employee){
        if (employeesManaging.contains(employee)) {
            throw new IllegalStateException(getName() + " is already managing " + employee.getName());
        } else {
            employeesManaging.add(employee);
        }


    }

    public ArrayList<Employee> getEmployeesManaging() {
        return employeesManaging;
    }

    //    @Override
//    public String toString() {
//
//        return  getName() + ": " + " Born: " + getDateOfBirth() + " com.starlingbank.company.entities.Salary: " + getSalary() + " com.starlingbank.company.entities.Salary with added bonus: " + bonusCalculator() ;
//    }


}
