package com.starlingbank.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.externalservices.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoursePersistenceService {
    private int nextFreeCourseId = 0;
    private List<Course> courses;
    private Map<Integer, List<Integer>> courseEnrollment;

    public CoursePersistenceService() {
        this.courseEnrollment = new HashMap<>();
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course newCourse) {
        if (courses.contains(newCourse)) {
            throw new IllegalStateException("Course " + newCourse.getName() + " is already in this course list");
        } else {
            courses.add(newCourse);
            courseEnrollment.put(newCourse.getId(), new ArrayList<>());
        }
    }

    public void enroll(int employeeId, int courseId) {

        if (!courseEnrollment.containsKey(courseId)) {
            throw new IllegalStateException("Course ID " + courseId + " not found");
        }

        if (courseEnrollment.get(courseId).contains(employeeId)) {
            throw new IllegalStateException("You have already signed up to this course");
        }

        List<Integer> peopleEnrolled = courseEnrollment.get(courseId);
        peopleEnrolled.add(employeeId);

    }

    public List<Course> listCourses(){
        List<Course> availableCourses = courses
                .entrySet() //Set<Map.Entry>
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        return courses;
    }

    public List<String> showWhatCoursesPersonIsEnrolledIn(int employeeId){
        List<String> arrOfCoursesSignedUpTo = new ArrayList<>();

        for ( Course course : courses) {
            if (courseEnrollment.get(course.getId()).contains(employeeId)) {
                arrOfCoursesSignedUpTo.add(course.getName());
            }
        }

        return arrOfCoursesSignedUpTo;
    }

}
