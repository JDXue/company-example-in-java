package com.starlingbank.externalservices;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.persistence.InMemoryCoursePersistenceService;

import java.util.List;


public class CourseService {
    private InMemoryCoursePersistenceService coursePersistenceService;

    public CourseService(InMemoryCoursePersistenceService coursePersistenceService) {
        this.coursePersistenceService = coursePersistenceService;
    }

    public void enroll(int employeeId, int courseId) {
        coursePersistenceService.enroll(employeeId, courseId);
    }

    public void addCourse(String newCourse) {
        coursePersistenceService.addCourse(newCourse);
    }

    public List<Course> getCourses() {
        return coursePersistenceService.listCourses();
    }

    public List<Course> showWhatCoursesPersonIsEnrolledIn(Employee person){
        return coursePersistenceService.showWhatCoursesPersonIsEnrolledIn(person.getEmployeeId());
    }

    //new method for getting courses for employees of manager
//    public Map<Integer, List<Integer>> getCourseEnrollment(){
//        coursePersistenceService.
//    }
}
