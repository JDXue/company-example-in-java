package com.starlingbank.externalservices;

import com.starlingbank.company.entities.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
    private List<Course> courses;
    private Map<Integer, List<String>> courseEnrollment;

    public CourseService() {
        this.courseEnrollment = new HashMap<>();
        this.courses = new ArrayList<>();
    }

    public void enroll(String personName, int courseId) {

        if (!courseEnrollment.containsKey(courseId)) {
            throw new IllegalStateException("Course ID " + courseId + " not found");
        }

        if (courseEnrollment.get(courseId).contains(personName)) {
            throw new IllegalStateException("You have already signed up to this course");
        }

        List<String> peopleEnrolled = courseEnrollment.get(courseId);
        peopleEnrolled.add(personName);

    }

    public void addCourse(Course newCourse) {
        if (courses.contains(newCourse)) {
            throw new IllegalStateException("Course " + newCourse.getName() + " is already in this course list");
        } else {
            courses.add(newCourse);
            courseEnrollment.put(newCourse.getId(), new ArrayList<>());
        }


    }

    public List<Course> getCourses() {
        return courses;
    }

    public Map<Integer, List<String>> getCourseEnrollment() {
        return courseEnrollment;
    }

    public List<String> showWhatCoursesPersonIsEnrolledIn(Employee person){
        List<String> arrOfCoursesSignedUpTo = new ArrayList<>();

        for ( Course course : courses) {
            if (courseEnrollment.get(course.getId()).contains(person.getName())) {
                arrOfCoursesSignedUpTo.add(course.getName());
            }
        }

        return arrOfCoursesSignedUpTo;
    }
}
