package services;

import Logic.BusinessLogic;
import dto.PromotionDTO;
import entities.Employee;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/employee")
public class EmployeeService {
    private BusinessLogic businessLogic;

    public EmployeeService() {
        businessLogic = new BusinessLogic();
    }

    @GET
    @Path("/{emp_no}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFullEmployeeRecord(@PathParam("emp_no") Integer emp_no) {

        // Validate input
        if (emp_no == null || emp_no <= 0) {
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


    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong";
    }

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