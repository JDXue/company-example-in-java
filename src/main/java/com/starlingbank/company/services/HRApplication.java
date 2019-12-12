package com.starlingbank.company.services;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.externalservices.Course;
import com.starlingbank.externalservices.CourseService;
import com.starlingbank.company.persistence.InMemoryEmployeePersistenceService;

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
        return employee.getSalaryObject().getAmount() * employee.getBonusPercentage() + (employee.getSalaryObject().getAmount() * EXTRA_HOURS_BONUS_PERCENTAGE);
    }

    public double calculateBaseBonusAmount(Employee employee) {
        return employee.getSalaryObject().getAmount() * employee.getBonusPercentage();
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

//    public Salary getEmployeeWithHighestSalary(List<Employee> listOfSalaries) {
//        double highestSalaryFound = 0.0;
//        String currencyType = "";
//
//        for (Salary salary : listOfSalaries) {
//            if (salary.getAmount() > highestSalaryFound) {
//                highestSalaryFound = salary.getAmount();
//            }
//        }
//
//        for (Salary salary : listOfSalaries) {
//            if (salary.getAmount() == highestSalaryFound) {
//                highestSalaryFound = salary.getAmount();
//                currencyType = salary.getCurrency();
//            }
//        }
//
//        return new Salary(highestSalaryFound, currencyType);
//
//    }

    //shows list of courses names employees are enrolled in for given team
    public List<String> showWhatCoursesMyEmployeesAreEnrolledIn(int managerId) {
        List<String> employeesEnrolledToCourses = new ArrayList<>();

        for (Employee employee : employeePersistenceService.getTeamMembers(managerId)) {
            List<String> courseNames = courseService.showWhatCoursesPersonIsEnrolledIn(employee);
            employeesEnrolledToCourses.addAll(courseNames);
        }

        return employeesEnrolledToCourses;
    }



}
