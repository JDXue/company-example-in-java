import com.starlingbank.company.persistence.DatabaseEmployeePersistenceService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import resources.AppResourceConfig;


public class Main {
//    public static void main(String[] args){
//        EmployeePersistenceService databaseEmployeePersistenceService = new DatabaseEmployeePersistenceService();
//        CoursePersistenceService databaseCoursePersistenceService = new DatabaseCoursePersistenceService();
//        List<Employee> listOfEmployees = databaseEmployeePersistenceService.listEmployees();
//
//        System.out.println(listOfEmployees);

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


//        databaseEmployeePersistenceService.addEmployeeToTeam(26,24);

//        List<Integer> teamIds = databaseEmployeePersistenceService.getTeam(26);
//        System.out.println(teamIds);

//        System.out.println(databaseEmployeePersistenceService.getTeamMembers(26));
//        System.out.println(databaseEmployeePersistenceService.getEmployeeWithHighestSalary());
//        System.out.println(databaseEmployeePersistenceService.getOldestEmployee());
//        System.out.println(databaseCoursePersistenceService.getEmployeeEnrolledInMostCourses());
//    }

    public static void main(String[] args) {

//        DatabaseEmployeePersistenceService databaseEmployeePersistenceService = new DatabaseEmployeePersistenceService();
//        databaseEmployeePersistenceService.removeTeamMember(26,23);

        Server server = new Server(8080); // Port 8080
        // startup code
        try {
            server.setHandler(getRESTEasyHandler());
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Handler getRESTEasyHandler() {
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        handler.setInitParameter("resteasy.guice.modules", CompanyModule.class.getCanonicalName());
        handler.addEventListener(new GuiceResteasyBootstrapServletContextListener());

        ServletHolder servlet = handler.addServlet(HttpServletDispatcher.class, "/");
//        servlet.setInitParameter("javax.ws.rs.Application",
//                AppResourceConfig.class.getCanonicalName());
        return handler;
    }
}
