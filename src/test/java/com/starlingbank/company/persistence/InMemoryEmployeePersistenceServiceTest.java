package com.starlingbank.company.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Programmer;
import com.starlingbank.company.entities.Salary;
import com.starlingbank.persistence.InMemoryEmployeePersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InMemoryEmployeePersistenceServiceTest {

    private InMemoryEmployeePersistenceService employeePersistenceService;

    @BeforeEach
    void setUp(){
        employeePersistenceService = new InMemoryEmployeePersistenceService();
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
        //Need to  make sure that the employee instances all have different ids

        //Given
        Salary salary = new Salary(33000, "GBP");


        //When
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);

        //Then
        int employeeId1 = employeePersistenceService.listEmployees().get(0).getEmployeeId();
        int employeeId2 = employeePersistenceService.listEmployees().get(1).getEmployeeId();
        assertThat(employeeId1).isNotEqualTo(employeeId2);

    }

    @Test
    void whenAddEmployeeToManage_shouldHaveEmployeeInManagerList(){
        //Given
        Salary salary = new Salary(33000, "GBP");

        employeePersistenceService.addNewManager("Alice","1982-08-18", salary);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);

        //When
//        employeePersistenceService.getEmployees();
        employeePersistenceService.addEmployeeToTeam(0,1);

        //Then
        List<Integer> employeesToManage = employeePersistenceService.getTeam(0);
        assertThat(employeesToManage).contains(1);
    }

    @Test
    void whenManagerAddsMultipleEmployeesToTeam_thenShouldReturnExpectedEmployees(){
        //Given
        Salary salary = new Salary(33000, "GBP");

        employeePersistenceService.addNewManager("Alice","1982-08-18", salary);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);
        employeePersistenceService.addNewProgrammer("Grace", "1982-08-18", salary);

        //When
        employeePersistenceService.addEmployeeToTeam(0,1);
        employeePersistenceService.addEmployeeToTeam(0,2);

        //Then
        assertThat(employeePersistenceService.getTeam(0)).containsExactly(1,2);

    }

    @Test
    void whenManagerAddsSameEmployeeToTeam_shouldOnlyAddEmployeeOnce(){
        //Given
        Salary salary = new Salary(33000, "GBP");
        employeePersistenceService.addNewManager("Alice","1982-08-18", salary);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);

        //When
        employeePersistenceService.addEmployeeToTeam(0,1);
        employeePersistenceService.addEmployeeToTeam(0,1);

        //Then
        assertThat(employeePersistenceService.getTeam(0)).containsOnlyOnce(1);

    }

    @Test
    void whenProgrammerTriesToAddEmployeeToTeamAsManager_thenShouldThrowException(){
        //Given
        Salary salary = new Salary(33000, "GBP");
        employeePersistenceService.addNewProgrammer("Alice","1982-08-18", salary);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", salary);

        //Then
        assertThatThrownBy(() -> employeePersistenceService.addEmployeeToTeam(0,1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("You do not have permission to be the owner of a team");

    }

    @Test
    void whenGetTeamWithManagerWithNoTeam_thenShouldReturnEmptyArray(){
        //Given
        Salary salary = new Salary(33000, "GBP");
        employeePersistenceService.addNewManager("Alice","1982-08-18", salary);

        //When & Then
        assertThat(employeePersistenceService.getTeam(0)).isEmpty();

    }

    @Test
    void givenUnknownEmployeeWhenAddingEmployeeToTeamThenShouldThrowException() {
        // Given
        Salary salary = new Salary(33000, "GBP");
        employeePersistenceService.addNewManager("Alice","1982-08-18", salary);

        // When

        // Then
        assertThatThrownBy(() -> employeePersistenceService.addEmployeeToTeam(0,29589))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("This employee does not exist");

        assertThat(employeePersistenceService.getTeam(0)).doesNotContain(29589);

    }

    @Test
    void whenGetEmployeeFromEmployees_thenShouldReturnExpectedObject(){
        // Given
        Salary salary = new Salary(33000, "GBP");
        employeePersistenceService.addNewManager("Alice","1982-08-18", salary);

        //When & Then
        assertThat(employeePersistenceService.getEmployeeFromEmployees(0)).isInstanceOf(Employee.class);
        assertThat(employeePersistenceService.getEmployeeFromEmployees(0).getName()).isEqualTo("Alice");

    }


}
