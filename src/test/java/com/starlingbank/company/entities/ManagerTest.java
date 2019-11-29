package com.starlingbank.company.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManagerTest {
    private static final Salary SALARY_DEFAULT = new Salary(20000, "GBP");



    @BeforeEach
    void setUp() {

    }

    @Test
    void whenAddNewEmployeeToManage_thenShouldHaveEmployeeInManagingList() {
        //Given
        Employee jeff = new Programmer("Jeff", "20/11/1984", SALARY_DEFAULT);
        Manager bob = new Manager("Bob", "20/12/1984", SALARY_DEFAULT);

        //When
        bob.addNewEmployeeToManage(jeff);

        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(jeff);

        //Then
        assertThat(bob.getEmployeesManaging()).isEqualTo(expectedEmployees);
    }

    @Test
    void whenEmployeeIsAddedMoreThanOnceToEmployeeManagerList_thenShouldOnlyExistOnceInEmployeeList() {
        //Given
        Employee jeff = new Programmer("Jeff", "20/11/1984", SALARY_DEFAULT);
        Manager bob = new Manager("Bob", "20/12/1984", SALARY_DEFAULT);

        //When
        bob.addNewEmployeeToManage(jeff);
        bob.addNewEmployeeToManage(jeff);

        //Then
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(jeff);

        assertThat(bob.getEmployeesManaging()).isEqualTo(expectedEmployees);
    }

}
