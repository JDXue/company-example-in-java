package com.starlingbank.externalservices;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.persistence.DatabaseCoursePersistenceService;
import com.starlingbank.company.persistence.InMemoryCoursePersistenceService;
import com.starlingbank.externalservices.Course;

import java.util.List;


public class DatabaseCourseService {
    private DatabaseCoursePersistenceService databaseCoursePersistenceService;

    public DatabaseCourseService(DatabaseCoursePersistenceService databaseCoursePersistenceService) {
        this.databaseCoursePersistenceService = databaseCoursePersistenceService;
    }

    public void enroll(int employeeId, int courseId) {
        databaseCoursePersistenceService.enroll(employeeId, courseId);
    }

    public void addCourse(String newCourse) {
        databaseCoursePersistenceService.addCourse(newCourse);
    }

    public List<Course> getCourses() {
        return databaseCoursePersistenceService.listCourses();
    }

    public List<Course> showWhatCoursesPersonIsEnrolledIn(Employee person){
        return databaseCoursePersistenceService.showWhatCoursesPersonIsEnrolledIn(person.getEmployeeId());
    }

    //new method for getting courses for employees of manager
//    public Map<Integer, List<Integer>> getCourseEnrollment(){
//        coursePersistenceService.
//    }
}
