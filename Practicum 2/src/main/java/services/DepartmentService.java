package services;

import Logic.BusinessLogic;
import entities.Department;
import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.*;
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
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        List<Department> departments = businessLogic.findAllDepartment();

        if (departments.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(departments).build();
    }

    // An endpoint for getting all employee records (info to return: employee number, first
    //     name, last name and hire date) from a given department number and a optional page
    //     number that defaults to 1. The results are to be paged and each page has a maximum
    //     of 20 records. User inputs a department number and an optional page number to
    //     retrieve the data. Note that in terms of computing, page numbers start from to
    //     but from the users point of view, pages start from to .
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
        // return Response.status(Response.Status.OK)
        //     .entity("Page number: " + page)
        //     .build();
    }
}
