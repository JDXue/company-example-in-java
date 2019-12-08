package com.starlingbank.company.services;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.externalservices.Course;
import com.starlingbank.externalservices.CourseService;
import com.starlingbank.persistence.InMemoryEmployeePersistenceService;

import java.util.ArrayList;
import java.util.List;

public class HRApplication {
    private static double EXTRA_HOURS_BONUS_PERCENTAGE = 0.05;
    private static int MAX_AMOUNT_EXTRA_HOURS = 50;

    private CourseService courseService;
    private InMemoryEmployeePersistenceService employeePersistenceService;

    public HRApplication(CourseService courseService, InMemoryEmployeePersistenceService employeePersistenceService) {
        this.courseService = courseService;
        this.employeePersistenceService = employeePersistenceService;
    }


    public double calculateBonus(Employee employee) {
        if (employee.getExtraHoursWorked() >= MAX_AMOUNT_EXTRA_HOURS) {
            return calculateBonusAmountForExtraHours(employee);

        } else {
            return calculateBaseBonusAmount(employee);
        }
    }

    public double calculateBonusAmountForExtraHours(Employee employee) {
        return employee.getSalary().getAmount() * employee.getBonusPercentage() + (employee.getSalary().getAmount() * EXTRA_HOURS_BONUS_PERCENTAGE);
    }

    public double calculateBaseBonusAmount(Employee employee) {
        return employee.getSalary().getAmount() * employee.getBonusPercentage();
    }

    public void enrollEmployeeToCourse(Employee employee, Course course) {
        courseService.enroll(employee.getEmployeeId(), course.getId());
    }

    public void annualReviewBonusUpdate(Employee employee, double bonusPercentageIncrement) {
        if (employee.hasHadAnnualReview()) {
            return;
        }
        employee.setHasHadAnnualReview(true);
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

    //shows list of courses names employees are enrolled in for given team
    //needs testing
    public List<String> showWhatCoursesMyEmployeesAreEnrolledIn(int managerId) {
        List<String> employeesEnrolledToCourses = new ArrayList<>();

        for (Employee employee : employeePersistenceService.getTeamMembers(managerId)) {
            List<String> courseNames = courseService.showWhatCoursesPersonIsEnrolledIn(employee);
            employeesEnrolledToCourses.addAll(courseNames);
        }

        return employeesEnrolledToCourses;
    }

}
