package com.starlingbank.company.persistence;

import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Salary;
import com.starlingbank.externalservices.Course;
import com.starlingbank.persistence.CoursePersistenceService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;

public class CoursePersistenceServiceTest {
    private Salary SALARY_DEFAULT = new Salary(20000, "GBP");
    private Manager bob = new Manager(2,"Bob", "20/12/1984", SALARY_DEFAULT);
    private CoursePersistenceService coursePersistenceService = new CoursePersistenceService();

//    @BeforeEach
//    void setUp() {
//        coursePersistenceService = new CoursePersistenceService();
//    }


    @Test
    void shouldAddCourseToCourseService() {
        //Given



        //When
        Course firstAid = new Course("firstAid", coursePersistenceService.getNextFreeCourseId());
        coursePersistenceService.addCourse(firstAid);
//        courseService.enroll("bob", 1);

        //Then
        List<Course> courses = coursePersistenceService.listCourses();
        assertThat(courses)
        .containsExactly(firstAid);

    }

    @Test
    void ifCourseToAddAlreadyInCourses_thenThrowException(){
        //Given
        Course firstAid = new Course("firstAid", coursePersistenceService.getNextFreeCourseId());
        coursePersistenceService.addCourse(firstAid);

        //When & Then
        assertThatThrownBy(() -> coursePersistenceService.addCourse(firstAid))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Course firstAid is already in this course list");
    }

    @Test
    void whenCourseNotFound_thenShouldThrowException() {
        //When & Then
        assertThatThrownBy(() -> coursePersistenceService.enroll(bob, 33))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Course ID 33 not found");
    }


    @Test
    void ifPersonEnrollsOnCourseAgain_thenShouldNotAddToCourseMoreThanOnce(){
        //Given
        Course firstAid = new Course("firstAid");
        coursePersistenceService.addCourse(firstAid);
        coursePersistenceService.enroll(bob, 0);

        //When
        coursePersistenceService.enroll(bob, 0);



        //Then
        List <Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(firstAid);

        assertThat(coursePersistenceService.listCourses()).isEqualTo(expectedCourses);


    }

    @Test
    void whenGetCourses_thenShouldHaveListOfCourses(){
        //Given
        Course firstAid = new Course("firstAid");
        coursePersistenceService.addCourse(firstAid);


        //Then
        List <Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(firstAid);

        assertThat(coursePersistenceService.listCourses()).isEqualTo(expectedCourses);

    }

    @Test
    void whenShowWhatCoursesEmployeesAreEnrolledIn_thenGetArrayOfCoursesTheyAreIn(){
        //Given
        Course firstAid = new Course("firstAid");
        coursePersistenceService.addCourse(firstAid);
        coursePersistenceService.enroll(bob, 0);

        //When


        //Then
        List <String> expectedCourses = new ArrayList<>();
        expectedCourses.add(firstAid.getName());

        assertThat(coursePersistenceService.showWhatCoursesPersonIsEnrolledIn(bob)).isEqualTo(expectedCourses);

    }

}
