package com.starlingbank.company.services;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.persistence.Database;
import com.starlingbank.company.persistence.DatabaseEmployeePersistenceService;
import com.starlingbank.externalservices.Course;
import com.starlingbank.externalservices.CourseService;
import com.starlingbank.company.persistence.InMemoryEmployeePersistenceService;
import com.starlingbank.externalservices.DatabaseCourseService;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHRApplication {
    private static double EXTRA_HOURS_BONUS_PERCENTAGE = 0.05;
    private static int MAX_AMOUNT_EXTRA_HOURS = 50;

    private DatabaseCourseService databaseCourseService;
    private DatabaseEmployeePersistenceService databaseEmployeePersistenceService;

    public DatabaseHRApplication(DatabaseCourseService databaseCourseService, DatabaseEmployeePersistenceService databaseEmployeePersistenceService) {
        this.databaseCourseService = databaseCourseService;
        this.databaseEmployeePersistenceService = databaseEmployeePersistenceService;
    }


//    public double calculateBonus(Employee employee) {
//        if (employee.getExtraHoursWorked() >= MAX_AMOUNT_EXTRA_HOURS) {
//            return calculateBonusAmountForExtraHours(employee);
//
//        } else {
//            return calculateBaseBonusAmount(employee);
//        }
//    }

//    public double calculateBonusAmountForExtraHours(Employee employee) {
//        return employee.getSalaryObject().getAmount() * employee.getBonusPercentage() + (employee.getSalaryObject().getAmount() * EXTRA_HOURS_BONUS_PERCENTAGE);
//    }
//
//    public double calculateBaseBonusAmount(Employee employee) {
//        return employee.getSalaryObject().getAmount() * employee.getBonusPercentage();
//    }

    public void enrollEmployeeToCourse(int employeeId, int courseId) {
        databaseCourseService.enroll(employeeId, courseId);
    }
//
//    public void annualReviewBonusUpdate(Employee employee, double bonusPercentageIncrement) {
//        if (employee.hasHadAnnualReview()) {
//            return;
//        }
//        employee.setHasHadAnnualReview(true);
//        employee.setBonusPercentage(employee.getBonusPercentage() + bonusPercentageIncrement);
//    }



    //shows list of courses names employees are enrolled in for given team
    public List<String> showWhatCoursesMyEmployeesAreEnrolledIn(int managerId) {
        List<String> employeesEnrolledToCourses = new ArrayList<>();

        for (Employee employee : databaseEmployeePersistenceService.getTeamMembers(managerId)) {
            List<String> courseNames = databaseCourseService.showWhatCoursesPersonIsEnrolledIn(employee);
            employeesEnrolledToCourses.addAll(courseNames);
        }

        return employeesEnrolledToCourses;
    }

    public void addNewManager(String name, String dateOfBirth, int salaryId){
        databaseEmployeePersistenceService.addNewManager(name, dateOfBirth, salaryId);
    }

    public void addNewProgrammer(String name, String dateOfBirth, int salaryId){
        databaseEmployeePersistenceService.addNewProgrammer(name, dateOfBirth, salaryId);
    }

    public List<Employee> listEmployees(){
        return databaseEmployeePersistenceService.listEmployees();
    }

    public void addEmployeeToTeam(int managerId, int employeeId){
        databaseEmployeePersistenceService.addEmployeeToTeam(managerId, employeeId);
    }

    public List<Integer> getTeam(int managerId){
        return databaseEmployeePersistenceService.getTeam(managerId);
    }

    public List<Employee> getTeamMembers(int managerId){
        return databaseEmployeePersistenceService.getTeamMembers(managerId);
    }

    public Employee getEmployeeFromEmployees(int employeeId){
        return databaseEmployeePersistenceService.getEmployeeFromEmployees(employeeId);
    }

    public Employee getEmployeeWithHighestSalary(){
        return databaseEmployeePersistenceService.getEmployeeWithHighestSalary();
    }

    public Employee getOldestEmployee(){
        return databaseEmployeePersistenceService.getOldestEmployee();
    }

    public void removeTeamMember(int managerId, int employeeId){
        databaseEmployeePersistenceService.removeEmployeeFromTeam(managerId,employeeId);
    }

    public void updateEmployeeName(int employeeId, String payload){
        databaseEmployeePersistenceService.changeEmployeeName(employeeId, payload);
    }

}

