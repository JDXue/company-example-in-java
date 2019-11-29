package com.starlingbank.company.services;

import com.starlingbank.company.entities.*;
import com.starlingbank.externalservices.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HRApplicationTest {

    private Salary salary;
    private HRApplication bonus;
    private Manager bob;
    private Programmer jeff;

    @BeforeEach
    void bonusCalculatorTestSetup(){
        CourseService courseService = new CourseService();
        salary = new Salary(20000, "GBP");
        bonus = new HRApplication(courseService);

        jeff = new Programmer("Jeff", "20/11/1984", salary);
        bob = new Manager("Bob", "20/12/1984", salary);


    }

    @Test
    void whenManagerPassed_shouldGetReturnedDouble(){
        //Given
        System.out.println(bob);
        Manager bob = new Manager("Bob", "20/12/1984", salary);


        //When
        double salaryWithBonus = bonus.bonusCalculator(bob);
        System.out.println(salaryWithBonus);

        //Then
        assertNotNull( salaryWithBonus );

    }

    @Test
    void whenProgrammerPassed_shouldGetReturnedDouble(){
        //Given


        //When
        double salaryWithBonus = bonus.bonusCalculator(jeff);
        System.out.println(salaryWithBonus);

        //Then
        assertNotNull( salaryWithBonus );

    }

    @Test
    void whenManagerPassed_shouldGetReturnedExpectedDouble(){
        //Given
        System.out.println(bob);

        //When
        double expectedSalary = 26000.0;
        double salaryWithBonus = bonus.bonusCalculator(bob);
        System.out.println(salaryWithBonus);

        //Then
        assertEquals( expectedSalary, salaryWithBonus, 0.01);

    }

    @Test
    void whenProgrammerPassed_shouldGetReturnedExpectedDouble(){
        //Given

        //When
        double expectedSalary = 24000.0;
        double salaryWithBonus = bonus.bonusCalculator(jeff);
        System.out.println(salaryWithBonus);

        //Then
        assertEquals( expectedSalary, salaryWithBonus, 0.01);

    }


//    @Disabled
    @Test
    void whenEmployeeHasAnnualReview_TheirBonusIsUpdated(){
        //Given
        Programmer jeff = new Programmer("Jeff", "20/11/1984", salary);

        //When
        double expectedSalary = 24200.0;
        jeff.haveAnnualReview(0.01); //already has 0.2 bonus


        double salaryWithBonus = bonus.bonusCalculator(jeff); //check bonus calculator after the annual review
        System.out.println(salaryWithBonus);

        //Then
        assertEquals(expectedSalary, salaryWithBonus);



    }

    @Test
    void whenEmployeeHasWorkedMoreThanFiftyHours_bonusShouldIncrease(){
        //Given
        Programmer jeff = new Programmer("Jeff", "20/11/1984", salary);

        //When
        double expectedSalary = 25000.0;
//        double salaryWithBonus = bonus.bonusCalculator(jeff);
        jeff.setExtraHoursWorked(50.0);

        double salaryWithBonus = bonus.bonusCalculator(jeff);

        System.out.println(salaryWithBonus);

        //Then
        assertEquals( expectedSalary , salaryWithBonus);

    }

    @Test
    void whenEmployeeIsGivenHighestSalary_shouldGetUpdatedWithExpectedValue(){
        //Given
        Programmer jeff = new Programmer("Jeff", "20/11/1984", salary);
        Manager bob = new Manager("Bob", "20/12/1984", salary);

        List<Employee> myEmployees = new ArrayList();
        myEmployees.add(jeff);
        myEmployees.add(bob);

        Company myCompany = new Company(myEmployees);

        System.out.println(myCompany);


        //When
        double expectedSalary = 20001.0;
        myCompany.giveEmployeeHighestSalary(jeff);

//        double actualHighestSalary = bonus.bonusCalculator(jeff);
        double actualHighestSalary = jeff.getSalary().getAmount();
//        System.out.println(actualHighestSalary);

        //Then
        assertEquals( expectedSalary , actualHighestSalary);

    }







}


