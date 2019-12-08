package com.starlingbank.company.services;

import com.starlingbank.company.entities.*;
import com.starlingbank.externalservices.Course;
import com.starlingbank.externalservices.CourseService;
import com.starlingbank.persistence.InMemoryEmployeePersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class HRApplicationTest {

    private static Salary SALARY_DEFAULT = new Salary(20000, "GBP");

    private HRApplication hrApplication;


    @Mock
    private CourseService courseService;
    @Mock
    private InMemoryEmployeePersistenceService employeePersistenceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        hrApplication = new HRApplication(courseService, employeePersistenceService);
    }

    @Test
    void givenManagerWithDefaultSalary_whenCalculateBonus_thenShouldReturnAManagerBonus() {
        //Given
        Employee employee = new Manager(1,"Bob", "20/12/1984", SALARY_DEFAULT);

        //When
        double bonusAmount = hrApplication.calculateBonus(employee);

        //Then
        double expectedBonus = 6000.0;
        assertEquals(expectedBonus, bonusAmount, 0.01);
    }

    @Test
    void whenProgrammerPassed_shouldGetReturnedProgrammerEmployeeBonus() {
        //Given
        Employee employee = new Programmer(1,"Jeff", "20/11/1984", SALARY_DEFAULT);

        //When
        double bonusAmount = hrApplication.calculateBonus(employee);

        //Then
        double expectedBonus = 4000.0;
        assertEquals(expectedBonus, bonusAmount, 0.01);
    }

    @Test
    void whenEmployeeHasAnnualReview_TheirBonusIsUpdated() {
        //Given
        Programmer jeff = new Programmer(1,"Jeff", "20/11/1984", SALARY_DEFAULT);

        //When
        hrApplication.annualReviewBonusUpdate(jeff, 0.01); //already has 0.2 bonus

        //Then
        double expectedBonusPercentage = 0.21;
        assertEquals(expectedBonusPercentage, jeff.getBonusPercentage(), 0.01);
    }

    @Test
    void whenEmployeeHasAlreadyHadAnnualReview_shouldNotUpdateBonusPercentage() {
        //Given
        Programmer jeff = new Programmer(1,"Jeff", "20/11/1984", SALARY_DEFAULT);
        jeff.setHasHadAnnualReview(true);

        //When
        hrApplication.annualReviewBonusUpdate(jeff, 0.01); //already has 0.2 bonus

        //Then
        assertThat(jeff.getBonusPercentage()).isEqualTo(0.2);
    }

    @Test
    void whenEmployeeHasWorkedMoreThanFiftyHours_bonusShouldIncrease() {
        //Given
        Programmer jeff = new Programmer(1,"Jeff", "20/11/1984", SALARY_DEFAULT);
        jeff.setExtraHoursWorked(50.0);

        //When
        double bonusAmount = hrApplication.calculateBonus(jeff);

        //Then
        double expectedBonusAmount = 5000.0;
        assertEquals(expectedBonusAmount, bonusAmount);
    }

    @Test
    void whenGetEmployeesWithHighestSalary_shouldGetExpectedListOfEmployees() {
        //Given
        Salary higherSalary = new Salary(300000, "GBP");
        Programmer jeff = new Programmer(1,"Jeff", "20/11/1984", SALARY_DEFAULT);
        Manager bob = new Manager(2,"Bob", "20/12/1984", higherSalary);

        List<Employee> myEmployees = new ArrayList();
        myEmployees.add(jeff);
        myEmployees.add(bob);

        //When


        //Then
        List<Employee> expectedListOfEmployees = new ArrayList<>();
        expectedListOfEmployees.add(bob);

        assertEquals(expectedListOfEmployees, hrApplication.getEmployeesWithHighestSalary(myEmployees));
    }

    @Test
    void ifManagerRequestsCourseEmployeesEnrolledIn_thenShouldReturnMapOfEmployeesAndCourses() {
        //Given
        Programmer jeff = new Programmer(1,"Jeff", "20/11/1984", SALARY_DEFAULT);
        Programmer alice = new Programmer(2,"Alice", "20/11/1984", SALARY_DEFAULT);
        Programmer ada = new Programmer(3,"Ada", "20/11/1984", SALARY_DEFAULT);

        List<Employee> team = new ArrayList<>();
        team.add(jeff);
        team.add(alice);
        team.add(ada);

        List<String> courses = new ArrayList<>();
        courses.add("first aid");

        when(employeePersistenceService.getTeamMembers(0)).thenReturn(team);
        when(courseService.showWhatCoursesPersonIsEnrolledIn(any(Employee.class))).thenReturn(courses);

        //When
        List<String> totalCourseLists = hrApplication.showWhatCoursesMyEmployeesAreEnrolledIn(0);

        //Then
        assertThat(totalCourseLists).hasSize(3);
        assertThat(totalCourseLists).containsOnly("first aid");

    }


    @Test
    void whenEnrollEmployeeToCourse_thenShouldHaveCourseAddedToEmployeeInCourses(){
        //Given
        Programmer ada = new Programmer(1,"Ada", "10/12/1815", SALARY_DEFAULT);
        Course firstAid = new Course(   1,"first aid");

        //When & Then
        List<String> expectedCourses = new ArrayList<>();
        expectedCourses.add("first aid");


        when(courseService.showWhatCoursesPersonIsEnrolledIn(any(Employee.class))).thenReturn(expectedCourses); //mock returned list

        hrApplication.enrollEmployeeToCourse(ada, firstAid);

        verify(courseService).enroll(any(Integer.class), any(Integer.class)); //check this is executed

    }


}


