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
    // private final EntityManagerFactory emf = BusinessLogic.getEntityManagerFactory();

    // public List<Department>findAllDepartment(){
    //     EntityManager em = emf.createEntityManager();
    //     List<Department> departments = new ArrayList<>();

    //     try {
    //        departments =  em.createQuery("SELECT d from Department d", Department.class).getResultList();

    //     } catch (Exception e) {
    //         System.err.println("Error fetching all departments: " + e.getMessage());
    //     } finally {
    //         if (em.isOpen()) {
    //             em.close();
    //         }
    //     }
    //     return departments;
    // }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments(){
        List<Department> departments = businessLogic.findAllDepartment();

        if (departments.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(departments).build();
    }
}
