package com.starlingbank.externalservices;

import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Salary;
import com.starlingbank.persistence.CoursePersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class CoursePersistenceServiceTest {


    private Salary SALARY_DEFAULT = new Salary(20000, "GBP");
    private Manager bob;
    private CoursePersistenceService coursePersistanceService = new CoursePersistenceService();
    
//    @BeforeEach
//    void setUp() {
//        coursePersistenceService = new CoursePersistenceService();
//    }


    @Test
    void shouldAddCourseToCourseService() {
        //Given
        Manager bob = new Manager("Bob", "20/12/1984", SALARY_DEFAULT);

        //When
        Course firstAid = new Course("firstAid", coursePersistanceService.getNextFreeCourseId());
        coursePersistanceService.addCourse(firstAid);
//        courseService.enroll("bob", 1);

        //Then
        List<Course> courses = coursePersistanceService.listCourses();
        assertThat(courses)
        .containsExactly(firstAid);

    }

    @Test
    void ifCourseToAddAlreadyInCourses_thenThrowException(){
        //Given
        Manager bob = new Manager("Bob", "20/12/1984", SALARY_DEFAULT);
        Course firstAid = new Course("firstAid", coursePersistanceService.getNextFreeCourseId());
        coursePersistanceService.addCourse(firstAid);

        //When & Then
        assertThatThrownBy(() -> coursePersistanceService.addCourse(firstAid))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Course firstAid is already in this course list");
    }

    @Test
    void whenCourseNotFound_thenShouldThrowException() {
        //When & Then
        assertThatThrownBy(() -> coursePersistanceService.enroll(1, 33))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Course ID 33 not found");
    }



    @Test
    void ifPersonEnrollsOnCourseAgain_thenShouldThrowException(){
        //Given
        Manager bob = new Manager("Bob", "20/12/1984", SALARY_DEFAULT);
        Course firstAid = new Course("firstAid");
        coursePersistanceService.addCourse(firstAid);
        coursePersistanceService.enroll(bob.getEmployeeId(), 0);

        //When
        coursePersistanceService.enroll(bob.getEmployeeId(), 0);

        List <Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(firstAid);

        //Then
        assertThat(coursePersistanceService.listCourses()).isEqualTo(expectedCourses);


    }

    @Test
    void whenGetCourses_thenShouldHaveListOfCourses(){
        //Given
        Course firstAid = new Course("firstAid");
        coursePersistanceService.addCourse(firstAid);

        List <Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(firstAid);

        //Then
        assertThat(coursePersistanceService.listCourses()).isEqualTo(expectedCourses);

    }

    @Test
    void whenShowWhatCoursesEmployeesAreEnrolledIn_thenGetArrayOfCoursesTheyAreIn(){
        //Given
        Manager bob = new Manager("Bob", "20/12/1984", SALARY_DEFAULT);
        Course firstAid = new Course("firstAid");
        coursePersistanceService.addCourse(firstAid);
        coursePersistanceService.enroll(bob.getEmployeeId(), 0);

        //When

        List <String> expectedCourses = new ArrayList<>();
        expectedCourses.add(firstAid.getName());

        //Then
        assertThat(coursePersistanceService.showWhatCoursesPersonIsEnrolledIn(bob.getEmployeeId())).isEqualTo(expectedCourses);

    }








}
