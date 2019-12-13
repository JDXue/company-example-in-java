package com.starlingbank.company.persistence;

import org.junit.jupiter.api.Test;

public class DatabaseEmployeePersistenceServiceTest {
    private DatabaseEmployeePersistenceService databaseEmployeePersistenceService;

    @Test
    void whenAddNewManager_thenShouldNotThrowException(){}

    @Test
    void whenAddNewProgrammer_thenShouldAddEmployeeToDatabase(){}

    @Test
    void whenAddingSameEmployeeMoreThenOnce_thenShouldThrowSQLException(){}


    @Test
    void whenListEmployees_thenShouldReturnListOfEmployeeObjects(){}

    @Test
    void whenAddEmployeeToTeam_thenShouldAddEmployeeToDatabase(){}

    @Test
    void whenGetTeam_thenShouldReturnExpectListOfIntegers(){}

    @Test
    void whenGetTeamMembers_thenShouldReturnExpectedListOfEmployeeObjects(){}

    @Test
    void whenGetEmployeeFromEmployees_thenShouldReturnExpectedEmployeeObject(){}

    @Test
    void whenGetEmployeeWithHighestSalary_thenShouldReturnExpectedEmployeeObject(){}

    @Test
    void whenGetOldestEmployee_thenShouldReturnExpectedEmployeeObject(){}

    @Test
    void whenRemoveTeamMember_thenShouldNoLongerHaveTeamRelationshipForEmployeeInDatabase(){}

    @Test
    void whenChangeEmployeeName_thenShouldHaveNewNameInDatabase(){}

    @Test
    void whenShowCoursesTeamIsEnrolledIn_thenShouldReturnExpectedListOfStrings(){}

    @Test
    void whenRemoveEmployee_thenShouldNoLongerHaveEmployeeInDatabase(){}

    @Test
    void whenRemoveTeam_thenShouldNotHaveTeamRelationshipsForGivenManagerInDatabase(){}


}
