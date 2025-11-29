import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import services.DepartmentService;
import services.EmployeeService;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Driver extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(EmployeeService.class);
        classes.add(DepartmentService.class);
        classes.add(ObjectMapperContextResolver.class);

        return classes;
    }
}