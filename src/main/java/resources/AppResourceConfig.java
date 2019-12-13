package resources;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class AppResourceConfig extends Application {
    Set<Class<?>> classes = new HashSet<>();
    public AppResourceConfig() {
        classes.add(EmployeesResources.class);
    }
    public Set<Class<?>> getClasses() {
        return classes;
    }
}






