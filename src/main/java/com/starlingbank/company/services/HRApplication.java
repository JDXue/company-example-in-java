package com.starlingbank.company.services;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Salary;
import com.starlingbank.externalservices.Course;
import com.starlingbank.externalservices.CourseService;
import com.starlingbank.persistence.CoursePersistenceService;
import com.starlingbank.persistence.HRApplicationPersistanceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HRApplication {


    private HRApplicationPersistanceService hrApplicationPersistanceService;


    public HRApplication(HRApplicationPersistanceService hrApplicationPersistanceService) {

        this.hrApplicationPersistanceService = hrApplicationPersistanceService;
    }


    public double calculateBonus(Employee employee) {
        return hrApplicationPersistanceService.calculateBonus(employee);
    }

    private double calculateBonusAmountForExtraHours(Employee employee) {
        return hrApplicationPersistanceService.calculateBonusAmountForExtraHours(employee);
    }

    private double calculateBaseBonusAmount(Employee employee) {
        return hrApplicationPersistanceService.calculateBaseBonusAmount(employee);
    }

    public void enrollEmployeeToCourse(Employee employee, Course course) {
        hrApplicationPersistanceService.enrollEmployeeToCourse(employee, course);
    }


    public Map<Employee, List<String>> showWhatCoursesEmployeesAreEnrolledIn(Manager manager) {
        return hrApplicationPersistanceService.showWhatCoursesEmployeesAreEnrolledIn(manager);
    }

    public void annualReviewBonusUpdate(Employee employee, double bonusPercentageIncrement) {
        hrApplicationPersistanceService.annualReviewBonusUpdate(employee, bonusPercentageIncrement);
    }

    public List<Employee> getEmployeesWithHighestSalary(List<Employee> listOfEmployees) {
        return hrApplicationPersistanceService.getEmployeesWithHighestSalary(listOfEmployees);
    }

}


//calculateManagerBonus(com.starlingbank.company.entities.Manager parameter) {
//    getManagerBonus(new com.starlingbank.company.entities.Manager { getBonus() {parameter.getBonus();});
//}


