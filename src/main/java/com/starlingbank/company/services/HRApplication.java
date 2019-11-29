package com.starlingbank.company.services;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Salary;
import com.starlingbank.externalservices.CourseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HRApplication {

    private static double EXTRA_HOURS_BONUS_PERCENTAGE = 0.05;
    private static int MAX_AMOUNT_EXTRA_HOURS = 50;

    private CourseService courseService;

    public HRApplication(CourseService courseService) {
        this.courseService = courseService;
    }


    public double calculateBonus(Employee employee) {
        if (employee.getExtraHoursWorked() >= MAX_AMOUNT_EXTRA_HOURS) {
            return calculateBonusAmountForExtraHours(employee);

        } else {
            return calculateBaseBonusAmount(employee);
        }
    }

    private double calculateBonusAmountForExtraHours(Employee employee) {
        return employee.getSalary().getAmount() * employee.getBonusPercentage() + (employee.getSalary().getAmount() * EXTRA_HOURS_BONUS_PERCENTAGE);
    }

    private double calculateBaseBonusAmount(Employee employee) {
        return employee.getSalary().getAmount() * employee.getBonusPercentage();
    }

    public void enrollEmployeeToCourse(Employee employee, int courseId) {
        courseService.enroll(employee.getName(), courseId);

        List<String> coursesEnrolledIn = new ArrayList<>();
        coursesEnrolledIn = courseService.showWhatCoursesPersonIsEnrolledIn(employee);

        employee.setCoursesEnrolledOn(coursesEnrolledIn);
    }


    public Map<Employee, List<String>> showWhatCoursesEmployeesAreEnrolledIn(Manager manager) {
        Map<Employee, List<String>> employeesEnrolledToCourses = new HashMap<>();

        for (Employee employee : manager.getEmployeesManaging()) {
            employeesEnrolledToCourses.put(employee, employee.getCoursesEnrolledOn());
        }

        return employeesEnrolledToCourses;

    }

    public void annualReviewBonusUpdate(Employee employee, double bonusPercentageIncrement) {
        if (employee.hasHadAnnualReview()) {
            return;
        }

        employee.setHasHadAnnualMeeting(true);
        employee.setBonusPercentage(employee.getBonusPercentage() + bonusPercentageIncrement);
    }

    public List<Employee> getEmployeesWithHighestSalary(List<Employee> listOfEmployees) {
        double highestSalaryFound = 0.0;
        List<Employee> employeesWithHighestSalary = new ArrayList<>();

        for (Employee employee : listOfEmployees) {
            if (employee.getSalary().getAmount() > highestSalaryFound) {
                highestSalaryFound = employee.getSalary().getAmount();
            }
        }

        for (Employee employee : listOfEmployees) {
            if (employee.getSalary().getAmount() == highestSalaryFound) {
                employeesWithHighestSalary.add(employee);
            }
        }

        return employeesWithHighestSalary;

    }

}


//calculateManagerBonus(com.starlingbank.company.entities.Manager parameter) {
//    getManagerBonus(new com.starlingbank.company.entities.Manager { getBonus() {parameter.getBonus();});
//}


