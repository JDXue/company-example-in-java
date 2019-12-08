import com.starlingbank.company.entities.*;
import com.starlingbank.persistence.CoursePersistenceService;
import com.starlingbank.persistence.DatabaseCoursePersistenceService;
import com.starlingbank.persistence.DatabaseEmployeePersistenceService;
import com.starlingbank.persistence.EmployeePersistenceService;

import java.util.List;


public class Main {
    public static void main(String[] args){
        EmployeePersistenceService databaseEmployeePersistenceService = new DatabaseEmployeePersistenceService();
        CoursePersistenceService databaseCoursePersistenceService = new DatabaseCoursePersistenceService();
        List<Employee> listOfEmployees = databaseEmployeePersistenceService.listEmployees();

        System.out.println(listOfEmployees);

//        databaseEmployeePersistenceService.addNewProgrammer("Ada", "1992-12-04",null);
//        databaseEmployeePersistenceService.addNewProgrammer("Ellen", "1992-12-04",null);
//        databaseEmployeePersistenceService.addNewManager("Grace", "1980-12-04",null);
//        databaseEmployeePersistenceService.addNewManager("Joanne", "1992-12-04",null);


//        List<Employee> listOfEmployeesAfterAdding = databaseEmployeePersistenceService.listEmployees();
//
//        System.out.println(listOfEmployeesAfterAdding);

//        databaseCoursePersistenceService.addCourse("java");
//        databaseCoursePersistenceService.addCourse("javascript");
//        databaseCoursePersistenceService.addCourse("first aid");



//        List<Course> courses = databaseCoursePersistenceService.listCourses();


//        System.out.println(courses);
//
//        databaseCoursePersistenceService.enroll(23,2);
//        databaseCoursePersistenceService.enroll(23,1);
//        databaseCoursePersistenceService.enroll(26,1);


//        List<String> coursesEnrolledOn = databaseCoursePersistenceService.showWhatCoursesPersonIsEnrolledIn(23);



//        System.out.println(coursesEnrolledOn);

//        Employee employee = databaseEmployeePersistenceService.getEmployeeFromEmployees(23);
//        System.out.println(employee);


//        databaseEmployeePersistenceService.addEmployeeToTeam(26,23);

//        List<Integer> teamIds = databaseEmployeePersistenceService.getTeam(26);
//        System.out.println(teamIds);

//        System.out.println(databaseEmployeePersistenceService.getTeamMembers(26));
//        System.out.println(databaseEmployeePersistenceService.getEmployeesWithHighestSalary());
        System.out.println(databaseEmployeePersistenceService.getOldestEmployee());
    }
}
