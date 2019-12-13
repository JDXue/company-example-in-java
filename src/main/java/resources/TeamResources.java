package resources;

import com.google.inject.Inject;
import com.starlingbank.company.persistence.EmployeePersistenceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("teams")
public class TeamResources {

    private EmployeePersistenceService employeePersistenceService;

    @Inject
    public TeamResources(EmployeePersistenceService employeePersistenceService) {
        this.employeePersistenceService = employeePersistenceService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/team-ids")
    public Response getTeam(@QueryParam("managerId") int managerId){
        System.out.println("Getting team for manager " + managerId);
        return Response.status(200).entity(employeePersistenceService.getTeam(managerId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/team-members")
    public Response getTeamMembers(@QueryParam("managerId") int managerId){
        System.out.println("Getting team members for manager " + managerId);
        return Response.status(200).entity(employeePersistenceService.getTeamMembers(managerId)).build();
    }

    @POST
    @Path("add-team-member")
    public void addEmployeeToTeam(TeamRequest teamRequest){
        System.out.println("Adding new employee to team " + teamRequest);
        employeePersistenceService.addEmployeeToTeam(teamRequest.getManagerId(), teamRequest.getEmployeeId());
    }

    @DELETE
    @Path("/remove-team-member")
    public void removeTeamMember(
            @QueryParam("managerId") int managerId,
            @QueryParam("employeeId") int employeeId
    ){
        employeePersistenceService.removeTeamMember(managerId, employeeId);
    }

    @DELETE
    @Path("/remove-team")
    public void removeTeam(
            @QueryParam("managerId") int managerId
    ){
        employeePersistenceService.removeTeam(managerId);
    }

}
