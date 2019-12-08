package com.starlingbank.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Salary;

import java.util.List;

public interface EmployeePersistenceService {
     void addNewManager(String name, String dateOfBirth, Salary salary);
     void addNewProgrammer(String name, String dateOfBirth, Salary salary);
     List<Employee> listEmployees();
     void addEmployeeToTeam(int managerId, int employeeId);
     List<Integer> getTeam(int managerId);
     List<Employee> getTeamMembers(int managerId);
     Employee getEmployeeFromEmployees(int employeeId);
     List<Employee> getEmployeesWithHighestSalary();
     Employee getOldestEmployee();
     Employee getEmployeeEnrolledInMostCourses();
}
