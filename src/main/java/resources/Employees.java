package resources;


import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.entities.Programmer;
import com.starlingbank.company.entities.Salary;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("employees")
public class Employees {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{employeeName}/{dateOfBirth}/{salaryAmount}")
    public Employee getEmployee(
            @PathParam("employeeName") String employeeName,
            @PathParam("dateOfBirth") String dateOfBirth,
            @PathParam("salaryAmount") int salaryAmount,
            @QueryParam("bonus") double bonus){
        Employee newProgrammer = new Programmer(1,employeeName, dateOfBirth,new Salary(salaryAmount, "GBP"));
        newProgrammer.setBonusPercentage(bonus);
        return newProgrammer;
    }

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


}
