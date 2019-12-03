package com.starlingbank.company.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Programmer;
import com.starlingbank.company.entities.Salary;
import com.starlingbank.persistence.EmployeePersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeePersistenceServiceTest {

    private EmployeePersistenceService employeePersistenceService;

    @BeforeEach
    void setUp(){
        employeePersistenceService = new EmployeePersistenceService();
    }


    @Test
    void whenAddNewManager_shouldHaveNewManagerInEmployeesMap(){
        //Given
        Salary salary = new Salary(33000, "GBP");

        //When
        employeePersistenceService.addNewManager("Alice","1982-08-18", salary);

        //Then
        assertThat(employeePersistenceService.listEmployees().get(0)).isInstanceOf(Manager.class);

    }

    @Test
    void whenAddNewProgrammer_shouldHaveNewProgrammerInEmployeesMap(){
        //Given
        Salary salary = new Salary(33000, "GBP");


        //When
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);

        //Then
        assertThat(employeePersistenceService.listEmployees().get(0)).isInstanceOf(Programmer.class);

    }

//    @Test
//    void whenAddedSameEmployeeMoreThanOnce_thenEmployeeShouldOnlyBeAddedOnce(){
//        //Given
//        Salary salary = new Salary(33000, "GBP");
//
//
//        //When
//        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);
//        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);
//
//        //Then
//        assertThat(employeePersistenceService.listEmployees().size()).isEqualTo(1);
////        assertThat(employeePersistenceService.getEmployees().keySet().c);
//    }

    @Test
    void whenAddedMoreThanOneEmployee_thenEmployeesShouldEachHaveUniqueId(){
        //Given
        Salary salary = new Salary(33000, "GBP");


        //When
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);

        //Then
        assertThat(employeePersistenceService.listEmployees()).containsOnlyOnce();
//        assertThat(employeePersistenceService.getEmployees().keySet().c);
    }

    @Test
    void whenAddEmployeeToManage_shouldHaveEmployeeInManagerList(){
        //Given
        Salary salary = new Salary(33000, "GBP");

        employeePersistenceService.addNewManager("Alice","1982-08-18", salary);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);

        //When
//        employeePersistenceService.getEmployees();
        employeePersistenceService.addEmployeeToTeam(1,2);

        //Then
        List<Integer> employeesToManage = employeePersistenceService.getTeam(1);
        assertThat(employeesToManage.contains(2));
    }



}
