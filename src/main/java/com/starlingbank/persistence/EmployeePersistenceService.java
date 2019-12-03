package com.starlingbank.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Programmer;
import com.starlingbank.company.entities.Salary;

import java.util.ArrayList;
import java.util.List;

public class EmployeePersistenceService {

    private List<Employee> employees = new ArrayList<>();

    public EmployeePersistenceService(){
        this.employees = employees;
    }

    public void addNewManager(String name, String dateOfBirth, Salary salary){
        Manager newManager = new Manager(name, dateOfBirth, salary);
        addToEmployees(newManager);
    }

    public void addNewProgrammer(String name, String dateOfBirth, Salary salary){
        Programmer newProgrammer = new Programmer(name, dateOfBirth, salary);
        addToEmployees(newProgrammer);
    }

    public List<Employee> listEmployees(){
        return employees;
    }

    public void addToEmployees(Employee employee) {
        employees.add(employee);
    }
}
