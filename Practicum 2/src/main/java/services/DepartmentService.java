package services;

import Logic.BusinessLogic;
import entities.Department;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * DepartmentService is a RESTful service that provides endpoints for managing departments.
 * It is used to get all departments and search for employees by department.
 * {@code @Path("/departments")} is the base path for the DepartmentService.
 * @author Ilyas & Wei Xian
 */
@Path("/departments")
public class DepartmentService {
    private BusinessLogic businessLogic;

    public DepartmentService() {
        businessLogic = new BusinessLogic();
    }

    /**
     * GET endpoint to retrieve all departments.
     * @return A list of {@link Department} objects.
     */
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        List<Department> departments = businessLogic.findAllDepartment();

        if (departments.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(departments).build();
    }

    /**
     * GET endpoint to search for employees by department.
     * @param dept_no The department number.
     * @param page The page number.
     * @return A list of {@link EmployeeDTO} objects.
     */
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesByDepartment(@QueryParam("dept_no") @DefaultValue("") String dept_no, @QueryParam("page") @DefaultValue("1") int page) {

        if (page < 1) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Page number must be greater than 0")
                    .build();
        }
        if (dept_no == null || dept_no.isEmpty()) {
            return Response.ok("No input for department number").build();
        }
        List<Department> departments;
        departments = businessLogic.findAllDepartment();
        boolean validDept = false;
        for (Department department : departments) {
            if (department.getDeptNo().equals(dept_no)) {
                validDept = true;
                break;
            }
        }
        if (!validDept){
            return Response.status(Response.Status.NOT_FOUND).entity("Invalid department number").build();
        }

        return Response.ok(businessLogic.findEmployeesByDepartment(dept_no, page)).build();
    }
}
