package services;

import Logic.BusinessLogic;
import dto.PromotionDTO;
import entities.Employee;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * EmployeeService is a RESTful service that provides endpoints for managing employees.
 * It is used to search for employees by number and promote employees.
 * {@code @Path("/employee")} is the base path for the EmployeeService.
 * @author Ilyas & Wei Xian
 */
@Path("/employee")
public class EmployeeService {
    private BusinessLogic businessLogic;

    public EmployeeService() {
        businessLogic = new BusinessLogic();
    }

    /**
     * GET endpoint to search for employees by number.
     * @param emp_no The employee number.
     * @return A {@link Employee} object.
     */
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFullEmployeeRecord(@QueryParam("emp_no") int emp_no) {

        // Validate input
        if (emp_no <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Employee number must be a positive integer.")
                    .build();
        }

        // Fetch employee
        Employee employee = businessLogic.findFullEmployeeRecord(emp_no);

        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Employee with emp_no " + emp_no + " not found.")
                    .build();
        }

        return Response.ok(employee).build();
    }


    /**
     * POST endpoint to promote an employee.
     * @param employee The {@link PromotionDTO} object containing the promotion details.
     * @return A {@link Response} object containing the response from the promotion.
     */
    @POST
    @Path("/promotion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response promotion(PromotionDTO employee) {
        if  (employee == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("No employee input.").build();
        }
        return businessLogic.promoteEmployee(employee);
    }
}