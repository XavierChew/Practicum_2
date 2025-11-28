package services;

import Logic.BusinessLogic;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/employee")
public class EmployeeService {
    private BusinessLogic businessLogic;

    public EmployeeService() {
        businessLogic = new BusinessLogic();
    }
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class EmployeeService {
//    private final EntityManagerFactory emf = BusinessLogic.getEntityManagerFactory();
//
//    EntityManager em = emf.createEntityManager();
//    List<Employee> employee = new ArrayList<>();
//
//
//    }
//
//    }


    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong";
    }
}