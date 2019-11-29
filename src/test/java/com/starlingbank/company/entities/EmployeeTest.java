package com.starlingbank.company.entities;

import com.starlingbank.externalservices.CourseService;
import com.starlingbank.company.services.HRApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeTest {

    @Mock
    private CourseService courseService;

    @BeforeEach
    void setUp(){
        initMocks(this);
    }


    @Test
    void ifEmployeeEnrollsToCourse_thenShouldShowTheCourseInTheirListOfEnrolledCourses(){
        //Given
        Salary salary = new Salary(20000, "GBP");
        Programmer ada = new Programmer("Ada","10/12/1815", salary);

        HRApplication hr = new HRApplication(courseService);


        List<String> expectedCourses = new ArrayList<>();
        expectedCourses.add("first aid");

        when(courseService.showWhatCoursesPersonIsEnrolledIn(ada)).thenReturn(expectedCourses);

        hr.enrollEmployeeToCourse(ada,0);




        //Then

        verify(courseService).enroll(ada.getName(), 0);
        assertThat(ada.getCoursesEnrolledOn()).isEqualTo(expectedCourses);


    }

    @Test
    void ifManagerRequestsCourseEmployeesEnrolledIn_thenShouldReturnMapOfEmployeesAndCourses(){
        //Given
        Salary salary = new Salary(20000, "GBP");
        Programmer ada = new Programmer("Ada","10/12/1815", salary);

        Manager michelle = new Manager("Michelle", "17/01/1964", salary);
        michelle.addNewEmployeeToManage(ada);

        HRApplication hr = new HRApplication(courseService);

        List<String> expectedCourses = new ArrayList<>();
        expectedCourses.add("first aid");

        Map<Employee, List<String>> expectedReturnedMapOfEmployees = new HashMap<>();
        expectedReturnedMapOfEmployees.put(ada, expectedCourses);

        when(courseService.showWhatCoursesPersonIsEnrolledIn(ada)).thenReturn(expectedCourses);

        hr.enrollEmployeeToCourse(ada,0);

        //When & Then
        assertThat(hr.showWhatCoursesMyEmployeesAreEnrolledIn(michelle)).isEqualTo(expectedReturnedMapOfEmployees);

    }










}
