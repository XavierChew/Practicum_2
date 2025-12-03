import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import services.DepartmentService;
import services.EmployeeService;

import java.util.HashSet;
import java.util.Set;

/**
 *  **Driver** is the main configuration class for the JAX-RS application.
 *
 * <p>It extends {@link Application} and is annotated with {@code @ApplicationPath} to define the base URI
 * for all RESTful resources and endpoints.</p>
 *
 * <h2>Configuration Details</h2>
 * <ul>
 * <li>**Base URI:** The application's resources are served under the path **"/api"**.</li>
 * <li>**Registered Classes:** It explicitly registers all root resource classes and providers
 * required by the application.</li>
 * </ul>
 *
 * @author Ilyas & Wei Xian (Based on previous entity authorship)
 * @see DepartmentService
 * @see EmployeeService
 * @see ObjectMapperContextResolver
 *
 */
@ApplicationPath("/api")
public class Driver extends Application {

    /**
     * Returns a set of all root resource and provider classes to be included in the JAX-RS application.
     *
     * @return A {@link Set} of classes containing the application's REST endpoints and providers.
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        // Register resource classes
        classes.add(EmployeeService.class);
        classes.add(DepartmentService.class);

        // Register providers
        classes.add(ObjectMapperContextResolver.class);

        return classes;
    }
}