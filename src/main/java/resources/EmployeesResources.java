package resources;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Programmer;
import com.starlingbank.company.entities.Salary;
import com.starlingbank.company.persistence.DatabaseCoursePersistenceService;
import com.starlingbank.company.persistence.DatabaseEmployeePersistenceService;
import com.starlingbank.company.services.DatabaseHRApplication;
import com.starlingbank.externalservices.Course;
import com.starlingbank.externalservices.CourseService;
import com.starlingbank.externalservices.DatabaseCourseService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("employees")
public class EmployeesResources {

    private DatabaseCourseService databaseCourseService;
    private DatabaseEmployeePersistenceService databaseEmployeePersistenceService;
    private DatabaseHRApplication databaseHRApplication;

    public EmployeesResources() {
        databaseEmployeePersistenceService = new DatabaseEmployeePersistenceService();
        databaseCourseService = new DatabaseCourseService(new DatabaseCoursePersistenceService());
        databaseHRApplication = new DatabaseHRApplication(databaseCourseService, databaseEmployeePersistenceService);
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("{employeeName}/{dateOfBirth}/{salaryAmount}")
//    public Employee getEmployee(
//            @PathParam("employeeName") String employeeName,
//            @PathParam("dateOfBirth") String dateOfBirth,
//            @PathParam("salaryAmount") int salaryAmount,
//            @QueryParam("bonus") double bonus){
//        Employee newProgrammer = new Programmer(1,employeeName, dateOfBirth,new Salary(salaryAmount, "GBP"));
//        newProgrammer.setBonusPercentage(bonus);
//        return newProgrammer;
//    }

//    @GET
//    @Produces(MediaType)

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @QueryParam("{bonus}")
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("ping")
//    public String ping(){
//        return "Hello there";
//    }
//
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("helloworld")
//    public String helloWorld(){
//        return "Hello world!";
//    }
//
//    @POST
//    @Path("print")
//    public void printMe(){
//        System.out.println("I have a printed line!");
//    }
//
//    @GET
//    @Path("{hello}")
//    public Response getUserById(@PathParam("hello") String id) {
//        return Response.status(200).entity("getUserById has been called, ID is " + id).build();
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEmployees(){
        System.out.println("listing employees");
        return Response.status(200).entity(databaseHRApplication.listEmployees()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/team-ids/{managerId}")
    public Response getTeam(@PathParam("managerId") int managerId){
        System.out.println("Getting team for manager " + managerId);
        return Response.status(200).entity(databaseHRApplication.getTeam(managerId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/team-members/{managerId}")
    public Response getTeamMembers(@PathParam("managerId") int managerId){
        System.out.println("Getting team members for manager " + managerId);
        return Response.status(200).entity(databaseHRApplication.getTeamMembers(managerId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("employee/{employeeId}")
    public Response getEmployee(@PathParam("employeeId") int employeeId){
        System.out.println("Getting employee " + employeeId);
        return Response.status(200).entity(databaseHRApplication.getEmployeeFromEmployees(employeeId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("highest-paid-employee")
    public Response getHighestPaidEmployee(){
        System.out.println("Getting highest paid employee");
        return Response.status(200).entity(databaseHRApplication.getEmployeeWithHighestSalary()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("oldest-employee")
    public Response getOldestEmployee(){
        System.out.println("Getting oldest employee");
        return Response.status(200).entity(databaseHRApplication.getOldestEmployee()).build();
    }

    @POST
    public void enrollEmployeeToCourse(CourseRequest courseRequest){
        System.out.println("Enrolling employee to course " + courseRequest);
        databaseHRApplication.enrollEmployeeToCourse(courseRequest.getEmployeeId(), courseRequest.getCourseId());
    }


    @POST
    public void createNewEmployee(EmployeeRequest employeeRequest){
        System.out.println("Creating a new employee" + employeeRequest);
        switch(employeeRequest.getEmployeeType()){
            case("MANAGER"):
                databaseHRApplication.addNewManager(
                        employeeRequest.getName(),
                        employeeRequest.getDateOfBirth(),
                        employeeRequest.getSalaryId()
                );

                break;

            case("PROGRAMMER"):
                databaseHRApplication.addNewProgrammer(
                        employeeRequest.getName(),
                        employeeRequest.getDateOfBirth(),
                        employeeRequest.getSalaryId()
                );
                break;

            default:
                throw new IllegalStateException("Could not find this type of employee, found type: "+ employeeRequest.getEmployeeType());
        }
    }

    @POST
    @Path("add-employee-to-team")
    public void addEmployeeToTeam(TeamRequest teamRequest){
        System.out.println("Adding new employee to team " + teamRequest);
        databaseHRApplication.addEmployeeToTeam(teamRequest.getManagerId(), teamRequest.getEmployeeId());
    }

    @PATCH
    @Path("update-employee/name/{employeeId}/{payload}")
    public void updateEmployeeName(
            @JsonProperty("employeeId") int employeeId,
            @JsonProperty("payload") String payload){

        System.out.println("updating employee " + employeeId + " with payload " + payload);
        databaseHRApplication.updateEmployeeName(employeeId,payload);
    }


    @DELETE
    @Path("remove-team-member/{managerId}/{employeeId}")
    public void removeTeamMember(
            @PathParam("managerId") int managerId,
            @PathParam("employeeId") int employeeId
    ){
        databaseHRApplication.removeTeamMember(managerId,employeeId);
    }




}
