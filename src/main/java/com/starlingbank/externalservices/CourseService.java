package com.starlingbank.externalservices;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.persistence.CoursePersistenceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
    private CoursePersistenceService coursePersistenceService;

    public CourseService(CoursePersistenceService coursePersistenceService) {
        this.coursePersistenceService = coursePersistenceService;
    }

    public void enroll(int employeeId, int courseId) {
        coursePersistenceService.enroll(employeeId,courseId);
    }

//    public void enroll(Employee employee, Course course) {
//        coursePersistenceService.enroll(employee.getEmployeeId(), course.getId());
//    }

    public void addCourse(Course newCourse) {
        coursePersistenceService.addCourse(newCourse);
    }

    public List<Course> getCourses() {
        return coursePersistenceService.listCourses();
    }

    public List<String> showWhatCoursesPersonIsEnrolledIn(Employee person){
        return coursePersistenceService.showWhatCoursesPersonIsEnrolledIn(person.getEmployeeId());
    }
}
