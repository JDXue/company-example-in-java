package com.starlingbank.company.entities;

import com.starlingbank.externalservices.CourseService;
import com.starlingbank.company.services.HRApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeTest {

    private static Salary SALARY_DEFAULT = new Salary(20000, "GBP");

    @Mock
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }


    @Test
    void ifEmployeeEnrollsToCourse_thenShouldShowTheCourseInTheirListOfEnrolledCourses() {
        //Given
        Programmer ada = new Programmer("Ada", "10/12/1815", SALARY_DEFAULT);
        HRApplication hr = new HRApplication(courseService);

        List<String> expectedCourses = new ArrayList<>();
        expectedCourses.add("first aid");

        //When
        when(courseService.showWhatCoursesPersonIsEnrolledIn(ada)).thenReturn(expectedCourses);
        hr.enrollEmployeeToCourse(ada, 0);

        //Then
        verify(courseService).enroll(ada.getEmployeeId(), 0);
        assertThat(ada.getCoursesEnrolledOn()).isEqualTo(expectedCourses);
    }


}
