import com.google.inject.AbstractModule;
import com.starlingbank.company.persistence.CoursePersistenceService;
import com.starlingbank.company.persistence.DatabaseCoursePersistenceService;
import com.starlingbank.company.persistence.DatabaseEmployeePersistenceService;
import com.starlingbank.company.persistence.EmployeePersistenceService;
import resources.EmployeesResources;

public class CompanyModule extends AbstractModule {

    @Override
    protected void configure() {
        //REST resources
        bind(EmployeesResources.class);

        //Binding services interfaces with services implementation
        bind(EmployeePersistenceService.class).to(DatabaseEmployeePersistenceService.class);
        bind(CoursePersistenceService.class).to(DatabaseCoursePersistenceService.class);
    }
}
