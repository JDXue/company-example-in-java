package com.starlingbank.company.entities;

public class Programmer extends Employee {

    public Programmer(String name, String dateOfBirth, Salary salary){
        super(name, dateOfBirth, salary);
        bonusPercentage = 0.2;

    }


    public void programApplication(){
        System.out.println("I am programming a cool application");
    }


//    @Override
//    public String toString() {
//
//        return  getName() + ": " + " Born: " + getDateOfBirth() + " com.starlingbank.company.entities.Salary: " + getSalary() + " com.starlingbank.company.entities.Salary with added bonus: " + bonusCalculator() ;
//    }
}
