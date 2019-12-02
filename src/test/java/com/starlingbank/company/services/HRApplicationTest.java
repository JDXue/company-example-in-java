package com.starlingbank.company.services;

import com.starlingbank.company.entities.*;
import com.starlingbank.externalservices.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class HRApplicationTest {

    private static Salary SALARY_DEFAULT = new Salary(20000, "GBP");

    private HRApplication hrApplication;

    @Mock
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        hrApplication = new HRApplication(courseService);
    }

    @Test
    void givenManagerWithDefaultSalary_whenCalculateBonus_thenShouldReturnAManagerBonus() {
        //Given
        Employee employee = new Manager("Bob", "20/12/1984", SALARY_DEFAULT);

        //When
        double bonusAmount = hrApplication.calculateBonus(employee);

        //Then
        double expectedBonus = 6000.0;
        assertEquals(expectedBonus, bonusAmount, 0.01);
    }

    @Test
    void whenProgrammerPassed_shouldGetReturnedProgrammerEmployeeBonus() {
        //Given
        Employee employee = new Programmer("Jeff", "20/11/1984", SALARY_DEFAULT);

        //When
        double bonusAmount = hrApplication.calculateBonus(employee);

        //Then
        double expectedBonus = 4000.0;
        assertEquals(expectedBonus, bonusAmount, 0.01);
    }

    @Test
    void whenEmployeeHasAnnualReview_TheirBonusIsUpdated() {
        //Given
        Programmer jeff = new Programmer("Jeff", "20/11/1984", SALARY_DEFAULT);

        //When
        hrApplication.annualReviewBonusUpdate(jeff, 0.01); //already has 0.2 bonus

        //Then
        double expectedBonusPercentage = 0.21;
        assertEquals(expectedBonusPercentage, jeff.getBonusPercentage(), 0.01);
    }

    @Test
    void whenEmployeeHasAlreadyHadAnnualReview_shouldNotUpdateBonusPercentage() {
        //Given
        Programmer jeff = new Programmer("Jeff", "20/11/1984", SALARY_DEFAULT);
        jeff.setHasHadAnnualReview(true);

        //When
        hrApplication.annualReviewBonusUpdate(jeff, 0.01); //already has 0.2 bonus

        //Then
        assertThat(jeff.getBonusPercentage()).isEqualTo(0.2);
    }

    @Test
    void whenEmployeeHasWorkedMoreThanFiftyHours_bonusShouldIncrease() {
        //Given
        Programmer jeff = new Programmer("Jeff", "20/11/1984", SALARY_DEFAULT);
        jeff.setExtraHoursWorked(50.0);

        //When
        double bonusAmount = hrApplication.calculateBonus(jeff);

        //Then
        double expectedBonusAmount = 5000.0;
        assertEquals(expectedBonusAmount, bonusAmount);
    }

    @Test
    void whenEmployeeIsGivenHighestSalary_shouldGetUpdatedWithExpectedValue() {
        //Given
        Salary higherSalary = new Salary(300000, "GBP");
        Programmer jeff = new Programmer("Jeff", "20/11/1984", SALARY_DEFAULT);
        Manager bob = new Manager("Bob", "20/12/1984", higherSalary);

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
        HRApplication hr = new HRApplication(courseService);
        Programmer ada = new Programmer("Ada", "10/12/1815", SALARY_DEFAULT);
        Manager michelle = new Manager("Michelle", "17/01/1964", SALARY_DEFAULT);
        michelle.addNewEmployeeToManage(ada);

        List<String> expectedCourses = new ArrayList<>();
        expectedCourses.add("first aid");
        ada.setCoursesEnrolledOn(expectedCourses);

        Map<Employee, List<String>> expectedReturnedMapOfEmployees = new HashMap<>();
        expectedReturnedMapOfEmployees.put(ada, expectedCourses);

        //When & Then
        assertThat(hr.showWhatCoursesEmployeesAreEnrolledIn(michelle)).isEqualTo(expectedReturnedMapOfEmployees);
    }


}


