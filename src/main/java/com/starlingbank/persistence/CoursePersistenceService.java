package com.starlingbank.persistence;

import com.starlingbank.externalservices.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoursePersistenceService {
    private int nextFreeCourseId = 0;
    private Map<Integer, Course> courses;
    private Map<Integer, List<Integer>> courseEnrollment; // employee, List<courseId>

    public CoursePersistenceService() {
        this.courseEnrollment = new HashMap<>();
        this.courses = new HashMap<>();
    }

    public void addCourse(Course newCourse) {
        if (courses.containsValue(newCourse)) {
            throw new IllegalStateException("Course " + newCourse.getName() + " is already in this course list");
        } else {
            courses.put(generateNewNextFreeCourseId(),newCourse);
            courseEnrollment.put(newCourse.getId(), new ArrayList<>());
        }
    }

    public int getNextFreeCourseId() {
        return nextFreeCourseId;
    }

    public int generateNewNextFreeCourseId() {
        return nextFreeCourseId++;
    }

    public void enroll(int employeeId, int courseId) {

        if (!courses.containsKey(courseId)) {
            throw new IllegalStateException("Course ID " + courseId + " not found");
        }

        if (courseEnrollment.containsKey(employeeId)) {
            if (courseEnrollment.get(employeeId).contains(courseId)){
                return;
            }
        }

        List<Integer> employeeCourses = courseEnrollment.computeIfAbsent(employeeId, list -> new ArrayList<>());
        employeeCourses.add(courseId);
    }

    public List<Course> listCourses(){
        return new ArrayList<>(courses.values());
    }

    public List<String> showWhatCoursesPersonIsEnrolledIn(int employeeId){
        List<Integer> employeeCourses = courseEnrollment.get(employeeId);

        List<String> collect = employeeCourses
                .stream()
                .map(courseId -> courses.get(courseId))
                .map(Course::getName)
                .collect(Collectors.toList());

        return collect;
    }

}
