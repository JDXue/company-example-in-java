package com.starlingbank.externalservices;

import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Salary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CourseServiceTest {

    private CourseService courseService;

    private Salary salary;
    private Manager bob;

    @BeforeEach
    void setUp() {
        courseService = new CourseService();

        salary = new Salary(20000, "GBP");
        bob = new Manager("Bob", "20/12/1984", salary);

    }


    @Test
    void shouldAddCourseToCourseService() {
        //Given

        //When
        Course firstAid = new Course("firstAid", courseService.getCourses().size());
        courseService.addCourse(firstAid);
//        courseService.enroll("bob", 1);

        //Then
        List<Course> courses = courseService.getCourses();
        assertThat(courses)
        .containsExactly(firstAid);

    }

    @Test
    void ifCourseToAddAlreadyInCourses_thenThrowException(){
        //Given
        Course firstAid = new Course("firstAid", courseService.getCourses().size());
        courseService.addCourse(firstAid);


        //When & Then
        assertThatThrownBy(() -> courseService.addCourse(firstAid))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Course firstAid is already in this course list");
    }

    @Test
    void whenCourseNotFound_thenShouldThrowException() {
        //When & Then
        assertThatThrownBy(() -> courseService.enroll(1, 33))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Course ID 33 not found");


    }

    @Test
    void whenPersonAddedToCourse_thenShouldHavePersonInCourseList(){
        //Given
        Course firstAid = new Course("firstAid", courseService.getCourses().size());
        courseService.addCourse(firstAid);
        courseService.enroll(bob.getEmployeeId(), 0);


        //When
        List<Integer> courses = courseService.getCourseEnrollment().get(0);

        //Then

        assertThat(courses.contains(bob.getName()));



    }

    @Test
    void ifPersonEnrollsOnCourseAgain_thenShouldThrowException(){
        //Given
        Course firstAid = new Course("firstAid", courseService.getCourses().size());
        courseService.addCourse(firstAid);
        courseService.enroll(bob.getEmployeeId(), 0);


        //Then
        assertThatThrownBy(() -> courseService.enroll(bob.getEmployeeId(), 0))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("You have already signed up to this course");


    }

    @Test
    void whenGetCourses_thenShouldHaveListOfCourses(){
        //Given
        Course firstAid = new Course("firstAid", courseService.getCourses().size());
        courseService.addCourse(firstAid);

        List <Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(firstAid);

        //Then
        assertThat(courseService.getCourses()).isEqualTo(expectedCourses);

    }

    @Test
    void whenShowWhatCoursesPersonIsEnrolledIn_thenGetArrayOfCoursesTheyAreIn(){
        //Given
        Course firstAid = new Course("firstAid", courseService.getCourses().size());
        courseService.addCourse(firstAid);
        courseService.enroll(bob.getEmployeeId(), 0);

        //When
        List <String> expectedCourses = new ArrayList<>();
        expectedCourses.add(firstAid.getName());

        //Then
        assertThat(courseService.showWhatCoursesPersonIsEnrolledIn(bob)).isEqualTo(expectedCourses);

    }








}
