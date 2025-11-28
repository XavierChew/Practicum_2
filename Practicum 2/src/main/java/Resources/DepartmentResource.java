// package Resources;

// import entities.Department;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;

// import services.DepartmentService;

// import java.util.List;;


// @Path("/departments")
// public class DepartmentResource {

//     private final DepartmentService departmentService = new DepartmentService();

//     @GET
//     @Produces(MediaType.APPLICATION_JSON)
//     public Response getAllDepartments(){
//         List<Department> departments = departmentService.findAllDepartment();

//         if (departments.isEmpty()) {
//             return Response.status(Response.Status.NO_CONTENT).build();
//         }
//         return Response.ok(departments).build();
//     }
// }
