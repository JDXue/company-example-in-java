package com.starlingbank.company.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManagerTest {
    private Salary salary;
    private Manager bob;
    private Programmer jeff;


    @BeforeEach
    void setUp() {
        salary = new Salary(20000, "GBP");
        jeff = new Programmer(1,"Jeff", "20/11/1984", salary);
        bob = new Manager(2,"Bob", "20/12/1984", salary);
    }

    @Test
    void whenAddNewEmployeeToManage_thenShouldHaveEmployeeInManagingList() {
        //Given

        //When
        bob.addNewEmployeeToManage(jeff);

        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(jeff);

        //Then
        assertThat(bob.getEmployeesManaging()).isEqualTo(expectedEmployees);
    }

    @Test
    void whenEmployeeIsAddedTwiceTeEmployeeManagerList_thenShouldThrowException() {
        //Given
        bob.addNewEmployeeToManage(jeff);

        //Then
        assertThatThrownBy(() -> bob.addNewEmployeeToManage(jeff))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Bob is already managing Jeff");
    }

}
