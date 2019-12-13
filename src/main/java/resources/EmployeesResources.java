package resources;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.inject.Inject;
import com.starlingbank.company.persistence.CoursePersistenceService;
import com.starlingbank.company.persistence.EmployeePersistenceService;
import com.starlingbank.externalservices.DatabaseCourseService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employees")
public class EmployeesResources {

    private EmployeePersistenceService employeePersistenceService;


    @Inject
    public EmployeesResources(EmployeePersistenceService employeePersistenceService){
        this.employeePersistenceService = employeePersistenceService;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEmployees(){
        System.out.println("listing employees");
        return Response.status(200).entity(employeePersistenceService.listEmployees()).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("employee")
    public Response getEmployee(@QueryParam("employeeId") int employeeId){
        System.out.println("Getting employee " + employeeId);
        return Response.status(200).entity(employeePersistenceService.getEmployeeFromEmployees(employeeId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("highest-paid-employee")
    public Response getHighestPaidEmployee(){
        System.out.println("Getting highest paid employee");
        return Response.status(200).entity(employeePersistenceService.getEmployeeWithHighestSalary()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("oldest-employee")
    public Response getOldestEmployee(){
        System.out.println("Getting oldest employee");
        return Response.status(200).entity(employeePersistenceService.getOldestEmployee()).build();
    }


    @POST
    public void createNewEmployee(EmployeeRequest employeeRequest){
        System.out.println("Creating a new employee" + employeeRequest);
        switch(employeeRequest.getEmployeeType()){
            case("MANAGER"):
                employeePersistenceService.addNewManager(
                        employeeRequest.getName(),
                        employeeRequest.getDateOfBirth(),
                        employeeRequest.getSalaryId()
                );

                break;

            case("PROGRAMMER"):
                employeePersistenceService.addNewProgrammer(
                        employeeRequest.getName(),
                        employeeRequest.getDateOfBirth(),
                        employeeRequest.getSalaryId()
                );
                break;

            default:
                throw new IllegalStateException("Could not find this type of employee, found type: "+ employeeRequest.getEmployeeType());
        }
    }




//    @PATCH
//    @Path("update-employee/name/{employeeId}/{payload}")
//    public void updateEmployeeName(
//            @JsonProperty("employeeId") int employeeId,
//            @JsonProperty("payload") String payload){
//
//        System.out.println("updating employee " + employeeId + " with payload " + payload);
//        employeePersistenceService.(employeeId,payload);
//    }






}
