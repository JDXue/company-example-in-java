package com.starlingbank.company.entities;

import java.util.List;

public class Company {

    private List<Employee> employees;

    public Company(List<Employee> employees){
        this.employees = employees;
    }


    @Override
    public String toString(){
        return "This company has " + employees.toString();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void giveEmployeeHighestSalary(Employee employeeToGiveHighest){
        double highestSalaryFound = 0.0;

        for( Employee employee : employees ){
            if ( employee.getSalary().getAmount() > highestSalaryFound ){
                highestSalaryFound = employee.getSalary().getAmount();
            }
        }

        Salary newHighestSalary = new Salary(highestSalaryFound + 1.0, "GBP");

        employeeToGiveHighest.setSalary(newHighestSalary);

        System.out.println(employeeToGiveHighest.getName()
                + " now has the highest salary in the company at "
                + employeeToGiveHighest.getSalary().getAmount()
                + employeeToGiveHighest.getSalary().getCurrency()
        );

    }
//
//    public void addEmployee(com.starlingbank.company.entities.Employee employee) {
//        this.employees = employees.add(employee);
//    }
}
