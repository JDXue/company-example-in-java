package com.starlingbank.company.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.externalservices.Course;

import java.util.List;

public interface CoursePersistenceService {

    void addCourse(String newCourse);
    void enroll(int employeeId, int courseId);
    List<Course> listCourses();
    List<Course> showWhatCoursesPersonIsEnrolledIn(int employeeId);
    Employee getEmployeeEnrolledInMostCourses();

}
