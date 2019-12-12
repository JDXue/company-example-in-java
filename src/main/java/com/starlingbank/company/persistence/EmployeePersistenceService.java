package com.starlingbank.company.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Salary;

import java.util.List;

public interface EmployeePersistenceService {

     void addNewManager(String name, String dateOfBirth, int salaryId);
     void addNewProgrammer(String name, String dateOfBirth, int salaryId);
     void addEmployeeToTeam(int managerId, int employeeId);

     List<Employee> listEmployees();
     List<Integer> getTeam(int managerId);
     List<Employee> getTeamMembers(int managerId);
     Employee getEmployeeFromEmployees(int employeeId);
     Employee getEmployeeWithHighestSalary();
     Employee getOldestEmployee();

//     void removeEmployee();
//     void removeTeamMember(int maangerId, int employeeId);
//     void removeTeam();


}
