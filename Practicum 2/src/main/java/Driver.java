import Resources.DepartmentResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Driver extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
//        classes.add(EmployeeResource.class);
        classes.add(DepartmentResource.class);
        return classes;
    }
}