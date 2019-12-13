package com.starlingbank.company.persistence;

import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Manager;
import com.starlingbank.company.entities.Programmer;
import com.starlingbank.company.entities.Salary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InMemoryEmployeePersistenceServiceTest {

    private InMemoryEmployeePersistenceService employeePersistenceService;
    private final Salary SALARY_DEFAULT = new Salary(20000, "GBP");

    @BeforeEach
    void setUp(){
        employeePersistenceService = new InMemoryEmployeePersistenceService();
    }


    @Test
    void whenAddNewManager_shouldHaveNewManagerInEmployeesMap(){
        //Given

        //When
        employeePersistenceService.addNewManager("Alice","1982-08-18", SALARY_DEFAULT);

        //Then
        assertThat(employeePersistenceService.listEmployees().get(0)).isInstanceOf(Manager.class);

    }

    @Test
    void whenAddNewProgrammer_shouldHaveNewProgrammerInEmployeesMap(){
        //Given


        //When
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", SALARY_DEFAULT);

        //Then
        assertThat(employeePersistenceService.listEmployees().get(0)).isInstanceOf(Programmer.class);

    }

//    @Test
//    void whenAddedSameEmployeeMoreThanOnce_thenEmployeeShouldOnlyBeAddedOnce(){
//        //Given
//        Salary SALARY_DEFAULT = new Salary(33000, "GBP");
//
//
//        //When
//        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", SALARY_DEFAULT);
//        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", SALARY_DEFAULT);
//
//        //Then
//        assertThat(employeePersistenceService.listEmployees().size()).isEqualTo(1);
////        assertThat(employeePersistenceService.getEmployees().keySet().c);
//    }

    @Test
    void whenAddedMoreThanOneEmployee_thenEmployeesShouldEachHaveUniqueId(){
        //Need to  make sure that the employee instances all have different ids

        //Given


        //When
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", SALARY_DEFAULT);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", SALARY_DEFAULT);

        //Then
        int employeeId1 = employeePersistenceService.listEmployees().get(0).getEmployeeId();
        int employeeId2 = employeePersistenceService.listEmployees().get(1).getEmployeeId();
        assertThat(employeeId1).isNotEqualTo(employeeId2);

    }

    @Test
    void whenAddEmployeeToManage_shouldHaveEmployeeInManagerList(){
        //Given

        employeePersistenceService.addNewManager("Alice","1982-08-18", SALARY_DEFAULT);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", SALARY_DEFAULT);

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

        employeePersistenceService.addNewManager("Alice","1982-08-18", SALARY_DEFAULT);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", SALARY_DEFAULT);
        employeePersistenceService.addNewProgrammer("Grace", "1982-08-18", SALARY_DEFAULT);

        //When
        employeePersistenceService.addEmployeeToTeam(0,1);
        employeePersistenceService.addEmployeeToTeam(0,2);

        //Then
        assertThat(employeePersistenceService.getTeam(0)).containsExactly(1,2);

    }

    @Test
    void whenManagerAddsSameEmployeeToTeam_shouldOnlyAddEmployeeOnce(){
        //Given
        employeePersistenceService.addNewManager("Alice","1982-08-18", SALARY_DEFAULT);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", SALARY_DEFAULT);

        //When
        employeePersistenceService.addEmployeeToTeam(0,1);
        employeePersistenceService.addEmployeeToTeam(0,1);

        //Then
        assertThat(employeePersistenceService.getTeam(0)).containsOnlyOnce(1);

    }

    @Test
    void whenProgrammerTriesToAddEmployeeToTeamAsManager_thenShouldThrowException(){
        //Given
        employeePersistenceService.addNewProgrammer("Alice","1982-08-18", SALARY_DEFAULT);
        employeePersistenceService.addNewProgrammer("Ada","1982-08-18", SALARY_DEFAULT);

        //Then
        assertThatThrownBy(() -> employeePersistenceService.addEmployeeToTeam(0,1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("You do not have permission to be the owner of a team");

    }

    @Test
    void whenGetTeamWithManagerWithNoTeam_thenShouldReturnEmptyArray(){
        //Given
        employeePersistenceService.addNewManager("Alice","1982-08-18", SALARY_DEFAULT);

        //When & Then
        assertThat(employeePersistenceService.getTeam(0)).isEmpty();

    }

    @Test
    void givenUnknownEmployeeWhenAddingEmployeeToTeamThenShouldThrowException() {
        // Given
        employeePersistenceService.addNewManager("Alice","1982-08-18", SALARY_DEFAULT);

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
        employeePersistenceService.addNewManager("Alice","1982-08-18", SALARY_DEFAULT);

        //When & Then
        assertThat(employeePersistenceService.getEmployeeFromEmployees(0)).isInstanceOf(Employee.class);
        assertThat(employeePersistenceService.getEmployeeFromEmployees(0).getName()).isEqualTo("Alice");

    }

    @Test
    void whenGetEmployeeWithHighestSalary_thenReturnExpectedObject(){
        //Given
        Salary aHigherSalary = new Salary(3000000, "GBP");
        employeePersistenceService.addNewManager("Alice","1982-08-18", aHigherSalary);
        employeePersistenceService.addNewProgrammer("Bob","1982-08-18", SALARY_DEFAULT);

        //When
        Employee employeeWithHighestSalary = employeePersistenceService.getEmployeeWithHighestSalary();

        //Then
        assertThat(employeeWithHighestSalary).isNotNull();
        assertThat(employeeWithHighestSalary.getName()).isEqualTo("Alice");
    }

    @Test
    void whenGetOldestEmployee_thenShouldReturnExpectedObject(){
        //Given
        employeePersistenceService.addNewManager("Alice","1982-08-18", SALARY_DEFAULT);
        employeePersistenceService.addNewProgrammer("Ada","1815-10-08", SALARY_DEFAULT);

        //When
        Employee oldestEmployee = employeePersistenceService.getOldestEmployee();


        //Then
        assertThat(oldestEmployee).isNotNull();
        assertThat(oldestEmployee.getName()).isEqualTo("Alice");
    }


}
