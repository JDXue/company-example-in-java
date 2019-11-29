package com.starlingbank.company.services;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.externalservices.CourseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HRApplication {

    private CourseService courseService;

    public HRApplication(CourseService courseService){
        this.courseService = courseService;
    }


    public double bonusCalculator(Employee employee) {
        double bonusCalculated = 0;
        double workingHoursBonus = 0.05;

        if (employee.getExtraHoursWorked() >= 50.0 ) {

            bonusCalculated = employee.getSalary().getAmount() + (employee.getSalary().getAmount() * employee.getBonus()) + (employee.getSalary().getAmount() * workingHoursBonus);
            System.out.println(bonusCalculated);
            return bonusCalculated;

        } else {

            bonusCalculated = employee.getSalary().getAmount() + (employee.getSalary().getAmount() * employee.getBonus());
            System.out.println(bonusCalculated);
            return bonusCalculated;

        }


    }

    public void enrollEmployeeToCourse(Employee employee, int courseId){
        courseService.enroll(employee.getName(), courseId);

        List<String> coursesEnrolledIn = new ArrayList<>();
        coursesEnrolledIn = courseService.showWhatCoursesPersonIsEnrolledIn(employee);

        employee.setCoursesEnrolledOn(coursesEnrolledIn);

        System.out.println(employee + " is now enrolled in " + coursesEnrolledIn);


    }

    public Map<Employee, List<String>> showWhatCoursesMyEmployeesAreEnrolledIn(Manager manager){

        Map<Employee, List<String>> employeesEnrolledToCourses = new HashMap<>();

        for (  Employee employee : manager.getEmployeesManaging() ){
            employeesEnrolledToCourses.put(employee, employee.getCoursesEnrolledOn());
        }

        System.out.println(employeesEnrolledToCourses);

        return employeesEnrolledToCourses;



    }



}


//calculateManagerBonus(com.starlingbank.company.entities.Manager parameter) {
//    getManagerBonus(new com.starlingbank.company.entities.Manager { getBonus() {parameter.getBonus();});
//}


