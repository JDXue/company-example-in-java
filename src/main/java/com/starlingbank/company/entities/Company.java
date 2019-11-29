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

//
//    public void addEmployee(com.starlingbank.company.entities.Employee employee) {
//        this.employees = employees.add(employee);
//    }
}
