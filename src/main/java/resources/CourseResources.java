package resources;

import com.google.inject.Inject;
import com.starlingbank.company.entities.Employee;
import com.starlingbank.company.persistence.CoursePersistenceService;
import com.starlingbank.externalservices.Course;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("courses")
public class CourseResources {
    private CoursePersistenceService coursePersistenceService;


    @Inject
    public void CourseResources(CoursePersistenceService coursePersistenceService){
        this.coursePersistenceService = coursePersistenceService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAvailableCourses(){
        return Response.status(200).entity(coursePersistenceService.listCourses()).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("show-courses-enrolled")
    public Response showWhatCoursesPersonEnrolledIn(@QueryParam("employeeId") int employeeId){
        return Response.status(200).entity(coursePersistenceService.showWhatCoursesPersonIsEnrolledIn(employeeId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("employee-in-most-courses")
    public Response getEmployeeEnrolledInMostCourses(){
        return Response.status(200).entity(coursePersistenceService.getEmployeeEnrolledInMostCourses()).build();
    }


    @POST
    @Path("add-course")
    public void addCourse(String courseName){
        coursePersistenceService.addCourse(courseName);
    }


    @POST
    @Path("enroll")
    public void enrollEmployeeToCourse(
            @QueryParam("courseId") int courseId,
            @QueryParam("employeeId") int employeeId
            ){
        System.out.println("Enrolling employee" + employeeId + "to course " + courseId);
        coursePersistenceService.enroll(employeeId, courseId);
    }





}
