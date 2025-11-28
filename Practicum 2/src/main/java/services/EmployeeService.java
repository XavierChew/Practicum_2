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

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong";
    }
}