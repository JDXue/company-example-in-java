package com.starlingbank.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Programmer;
import com.starlingbank.company.entities.Salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeePersistenceService {

    private Map<Integer, Employee> employees;
    private Map<Integer, List<Integer>> teams;
    private int nextFreeEmployeeId = 0;

    public EmployeePersistenceService(){
        this.employees = new HashMap<>();
        this.teams = new HashMap<>();
    }

    public void addNewManager(String name, String dateOfBirth, Salary salary){
        int employeeId = generateNextFreeEmployeeId();
        Manager newManager = new Manager(employeeId, name, dateOfBirth, salary);
        addToEmployees(newManager);
    }

    public void addNewProgrammer(String name, String dateOfBirth, Salary salary){
        int employeeId = generateNextFreeEmployeeId();
        Programmer newProgrammer = new Programmer(employeeId, name, dateOfBirth, salary);
        addToEmployees(newProgrammer);
    }

    public List<Employee> listEmployees(){
        return new ArrayList<>(employees.values());
    }

    private Map<Integer, Employee>  getEmployees() {
        return employees;
    }

    private void addToEmployees(Employee employee) {
        if (employees.containsValue(employee)) {
            return; //will not add the employee if same instance is included in the
        }
        employees.put(employee.getEmployeeId(), employee);
    }

    private int generateNextFreeEmployeeId(){
        return nextFreeEmployeeId++;
    }


    public void addEmployeeToTeam(int managerId, int employeeId){

        if (employees.get(managerId) instanceof Manager) {
            List<Integer> teamMembers = new ArrayList<>();

            if(teams.get(managerId) != null) {

                //if employee is already in the team they should not be added more thn once
                if (getTeam(managerId).contains(employeeId)){
                    return;
                }

                teamMembers = teams.get(managerId);
            }

            teamMembers.add(employeeId);
            teams.put(managerId, teamMembers);

        } else {

            throw new IllegalStateException("You do not have permission to be the owner of a team");
        }

    }


    public List<Integer> getTeam(int managerId) {
        if (teams.get(managerId) == null){
            return new ArrayList<>();
        }
       return teams.get(managerId);
    }

    public int getNextFreeEmployeeId() {
        return nextFreeEmployeeId;
    }
}
