package services;

import Logic.BusinessLogic;
import entities.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;


@Path("/departments")
public class DepartmentService {
    private BusinessLogic businessLogic;

    public DepartmentService() {
        businessLogic = new BusinessLogic();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments(){
        List<Department> departments = businessLogic.findAllDepartment();

        if (departments.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(departments).build();
    }
}
