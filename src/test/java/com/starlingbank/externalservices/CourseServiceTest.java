package com.starlingbank.externalservices;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.persistence.InMemoryCoursePersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CourseServiceTest {
    @Mock
    private InMemoryCoursePersistenceService coursePersistenceService;

    private CourseService courseService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        this.courseService = new CourseService(coursePersistenceService);
    }

    @Test
    void whenCallEnroll_thenShouldExecuteCoursePersistenceService(){
        //Given
        Employee employee = new Manager(1,"Bob", "20/12/1984", null);
        Course course = new Course(1,"First Aid");

        //When
        courseService.enroll(employee.getEmployeeId(), course.getId());

        //Then
        verify(coursePersistenceService, times(1)).enroll(any(Integer.class), any(Integer.class));
    }

    @Test
    void whenCallAddCourse_thenShouldExecuteCoursePersistenceService(){
        //Given

        //When
        courseService.addCourse("Java");

        //Then
        verify(coursePersistenceService, times(1)).addCourse("Java");
    }

    @Test
    void whenCallGetCourses_thenShouldExecuteCoursePersistenceService(){
        //Given

        //When
        courseService.getCourses();

        //Then
        verify(coursePersistenceService, times(1)).listCourses();
    }

    @Test
    void whenCallShowWhatCoursesPersonIsEnrolledIn_thenShouldExecuteCoursePersistenceService(){
        //Given
        Employee employee = new Manager(1,"Bob", "20/12/1984", null);

        //When
        courseService.showWhatCoursesPersonIsEnrolledIn(employee);

        //Then
        verify(coursePersistenceService, times(1)).showWhatCoursesPersonIsEnrolledIn(employee.getEmployeeId());
    }
}
