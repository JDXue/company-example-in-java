package com.starlingbank.company.persistence;

import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Salary;
import com.starlingbank.externalservices.Course;
import com.starlingbank.persistence.InMemoryCoursePersistenceService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;

public class InMemoryCoursePersistenceServiceTest {
    private Salary SALARY_DEFAULT = new Salary(20000, "GBP");
    private Manager bob = new Manager(2,"Bob", "20/12/1984", SALARY_DEFAULT);
    private InMemoryCoursePersistenceService coursePersistenceService = new InMemoryCoursePersistenceService();

//    @BeforeEach
//    void setUp() {
//        coursePersistenceService = new CoursePersistenceService();
//    }


    @Test
    void shouldAddCourseToCourseService() {
        //Given



        //When
        Course firstAid = new Course(coursePersistenceService.getNextFreeCourseId(), "firstAid");
        coursePersistenceService.addCourse("firstAid");
//        courseService.enroll("bob", 1);

        //Then
        List<Course> courses = coursePersistenceService.listCourses();
        assertThat(courses).hasSize(1);
        assertThat(courses.get(0).getName()).isEqualTo("firstAid");

    }

//    @Test
//    void ifCourseToAddAlreadyInCourses_thenThrowException(){
//        //Given
//        Course firstAid = new Course(coursePersistenceService.getNextFreeCourseId(), "firstAid");
//        coursePersistenceService.addCourse("firstAid");
//
//        //When & Then
//        assertThatThrownBy(() -> coursePersistenceService.addCourse("firstAid"))
//                .isInstanceOf(IllegalStateException.class)
//                .hasMessageContaining("Course firstAid is already in this course list");
//    }

    @Test
    void whenCourseNotFound_thenShouldThrowException() {
        //When & Then
        assertThatThrownBy(() -> coursePersistenceService.enroll(bob.getEmployeeId(), 33))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Course ID 33 not found");
    }


    @Test
    void ifPersonEnrollsOnCourseAgain_thenShouldNotAddToCourseMoreThanOnce(){
        //Given
        Course firstAid = new Course(1, "firstAid");
        coursePersistenceService.addCourse("firstAid");
        coursePersistenceService.enroll(bob.getEmployeeId(), 0);

        //When
        coursePersistenceService.enroll(bob.getEmployeeId(), 0);



        //Then
        List <Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(firstAid);

        assertThat(coursePersistenceService.showWhatCoursesPersonIsEnrolledIn(2)).hasSize(1);


    }

    @Test
    void whenGetCourses_thenShouldHaveListOfCourses(){
        //Given
        Course firstAid = new Course(1, "firstAid");
        coursePersistenceService.addCourse("firstAid");


        //Then
        List <Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(firstAid);

        assertThat(coursePersistenceService.listCourses()).hasSize(1);

    }

    @Test
    void whenShowWhatCoursesEmployeesAreEnrolledIn_thenGetArrayOfCoursesTheyAreIn(){
        //Given
        Course firstAid = new Course(1, "firstAid");
        coursePersistenceService.addCourse("firstAid");
        coursePersistenceService.enroll(bob.getEmployeeId(), 0);

        //When


        //Then
        List <String> expectedCourses = new ArrayList<>();
        expectedCourses.add(firstAid.getName());

        assertThat(coursePersistenceService.showWhatCoursesPersonIsEnrolledIn(bob.getEmployeeId())).isEqualTo(expectedCourses);

    }

}
