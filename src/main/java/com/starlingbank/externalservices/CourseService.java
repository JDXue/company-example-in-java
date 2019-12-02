package com.starlingbank.externalservices;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.persistence.CoursePersistenceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
    private List<Course> courses;
    private Map<Integer, List<Integer>> courseEnrollment;
    private CoursePersistenceService coursePersistenceService;

    public CourseService() {
        this.courseEnrollment = new HashMap<>();
        this.courses = new ArrayList<>();
        this.coursePersistenceService = new CoursePersistenceService();
    }

    public void enroll(int employeeId, int courseId) {
        coursePersistenceService.enroll(employeeId,courseId);
    }

    public void addCourse(Course newCourse) {
        coursePersistenceService.addCourse(newCourse);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Map<Integer, List<Integer>> getCourseEnrollment() {
        return courseEnrollment;
    }

    public List<String> showWhatCoursesPersonIsEnrolledIn(Employee person){
        List<String> arrOfCoursesSignedUpTo = coursePersistenceService.showWhatCoursesPersonIsEnrolledIn(person.getEmployeeId());

        return arrOfCoursesSignedUpTo;
    }
}
