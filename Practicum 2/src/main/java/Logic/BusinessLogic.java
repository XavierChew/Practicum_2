package Logic;

 import entities.DepartmentEmployee;
 import entities.DepartmentManager;
 import entities.Employee;
 import jakarta.persistence.*;
 import entities.Department;
 import jakarta.persistence.EntityManager;
 import java.util.List;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.ArrayList;
 import dto.EmployeeDTO;
 import dto.PromotionDTO;
 import jakarta.ws.rs.core.Response;
 import entities.Titles;
 import java.time.LocalDate;
 import entities.Salaries;

 /**
  * BusinessLogic class is the main class for the employee service.
  * It is responsible for the business logic of the employee service.
  * It is responsible for the data access of the employee service.
  * It is responsible for the validation of the employee service.
  * It is responsible for the promotion of the employee service.
  * @author: Ilyas & Wei Xian
  */
 public class  BusinessLogic {

     private static final String DBNAME = "employees";
     private static EntityManagerFactory emf = null;
     @PersistenceContext
     private static EntityManager em = null;

     public BusinessLogic() {
         emf = getEntityManagerFactory();
         em = emf.createEntityManager();
     }

     /**
      * getEntityManagerFactory method is used to get the entity manager factory.
      * @return EntityManagerFactory
      */
     public static EntityManagerFactory getEntityManagerFactory() {

         if (emf == null) {
             Map<String, String> persistenceMap = new HashMap<>();
             persistenceMap.put("jakarta.persistence.jdbc.url",
                     "jdbc:mariadb://localhost:3306/" + DBNAME);
             try {
                 emf = Persistence.createEntityManagerFactory("EmployeeService", persistenceMap);

             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
         }
         return emf;
     }

     /**
      * findAllDepartment method is used to find all departments.
      * @return List<Department>
      */
     public List<Department> findAllDepartment() {
         EntityManager em = emf.createEntityManager();
         List<Department> departments = new ArrayList<>();

         try {
             departments = em.createQuery("SELECT d from Department d", Department.class).getResultList();

         } catch (Exception e) {
             System.err.println("Error fetching all departments: " + e.getMessage());
         } finally {
             if (em.isOpen()) {
                 em.close();
             }
         }
         return departments;
     }

     /**
      * findFullEmployeeRecord method is used to find a full employee record.
      * @param empNo
      * @return Employee
      */
     public Employee findFullEmployeeRecord(int empNo) {
         EntityManager em = emf.createEntityManager();
         try {
             Employee emp = em.find(Employee.class, empNo);
             if (emp != null) {
                 // Initialize collections
                 emp.getTitleList().size();
                 emp.getSalaryList().size();
                 emp.getDeptEmpList().size();
                 emp.getDeptManagerList().size();


                 for (DepartmentEmployee de : emp.getDeptEmpList()) {
                     de.getDepartment().getDeptName();
                 }
                 // Initialize nested Department in DepartmentManager
                 for (DepartmentManager dm : emp.getDeptManagerList()) {
                     dm.getDepartment().getDeptName();
                 }
             }
             return emp;
         } finally {
             em.close();
         }
     }

     /**
      * findEmployeesByDepartment method is used to find employees by department.
      * @param dept_no  The unique identifier of the department.
      * @param page Page number
      * @return List<EmployeeDTO>
      */
     public List<EmployeeDTO> findEmployeesByDepartment(String dept_no, int page) {
         EntityManager em = emf.createEntityManager();
         List<EmployeeDTO> employees = new ArrayList<>();
         try {
             employees = em.createQuery(
                             "SELECT new dto.EmployeeDTO(e.emp_no, e.first_name, e.last_name, e.hire_date) " +
                                     "FROM Employee e " +
                                     "JOIN e.deptEmpList de " +
                                     "WHERE de.department.deptNo = :dept_no",
                             EmployeeDTO.class)
                     .setParameter("dept_no", dept_no)
                     .setFirstResult((page - 1) * 20)
                     .setMaxResults(20)
                     .getResultList();
         } catch (Exception e) {
             System.err.println("Error fetching employees by department: " + e.getMessage());
         } finally {
             if (em.isOpen()) {
                 em.close();
             }
         }
         return employees;
     }

     /**
      * promoteEmployee method is used to promote an employee.
      * @param promotion Promotion DTO
      * @return Response
      */
     public Response promoteEmployee(PromotionDTO promotion) {
         EntityManager em = emf.createEntityManager();
         em.getTransaction().begin();
         LocalDate toPresentDate = LocalDate.of(9999, 1, 1);
         System.out.println(promotion.toString());
         try {
             Employee employee = em.find(Employee.class, promotion.getEmpNo());
             if (employee == null) {
                 return Response.status(Response.Status.NOT_FOUND)
                         .entity("No employee record for ID: " + promotion.getEmpNo()).build();
             }
             if (promotion.getTitle() == null && promotion.getDepartmentID() == null && promotion.getSalary() == 0) {
                 return Response.ok("No details provided for promotion").build();
             }
             if (promotion.getSalary() < 0) {
                 return Response.ok("Invalid salary input").build();
             }
             Department department = em.find(Department.class, promotion.getDepartmentID());
             if (department == null) {
                 return Response.ok("Invalid department ID").build();
             }
             if (promotion.getTitle() != null) {
             System.out.println("Title update:");
                 em.createNamedQuery("Titles.updateTitleDate")
                         .setParameter("toDate", LocalDate.now())
                         .setParameter("emp_no", promotion.getEmpNo())
                         .setParameter("date", toPresentDate)
                         .executeUpdate();
             }
                Titles title = new Titles(promotion.getEmpNo(), promotion.getTitle(), LocalDate.now());
                em.persist(title);
             if (promotion.getSalary() != 0) {
                 System.out.println("Salary update:");
                 em.createNamedQuery("Salaries.updateSalaryDate")
                         .setParameter("toDate", LocalDate.now())
                         .setParameter("emp_no", promotion.getEmpNo())
                         .setParameter("date", toPresentDate)
                         .executeUpdate();
                 Salaries salary = new Salaries(promotion.getEmpNo(), promotion.getSalary(), LocalDate.now());
                 em.persist(salary);
             }
             if (promotion.getDepartmentID() != null) {
                 System.out.println("Department update:");
                 em.createNamedQuery("DepartmentEmployee.updateDepartmentDate")
                         .setParameter("toDate", LocalDate.now())
                         .setParameter("emp_no", promotion.getEmpNo())
                         .setParameter("date", toPresentDate)
                         .executeUpdate();
                 DepartmentEmployee deptEmp = new DepartmentEmployee(promotion.getEmpNo(), promotion.getDepartmentID(), LocalDate.now());
                 em.persist(deptEmp);
             }

             if (promotion.getTitle().toLowerCase().equals("manager")) {
                 if (promotion.getDepartmentID() == null) {
                     System.out.println("dept id: "+ employee.getDeptEmpList().get(0).getDeptNo());
                     promotion.setDepartmentID(employee.getDeptEmpList().get(0).getDeptNo());
                 }
                 DepartmentManager deptManager = new DepartmentManager(promotion.getDepartmentID(), promotion.getEmpNo(), LocalDate.now());
                 em.persist(deptManager);
             }

             em.getTransaction().commit();

             return Response.status(Response.Status.OK)
                     .entity("Employee with emp_no " + promotion.getEmpNo() + " promoted to " + promotion.getTitle() + ".")
                     .build();
         } catch (Exception e) {
             em.getTransaction().rollback();
             throw new RuntimeException("Error promoting employee: " + e.getMessage());
         } finally {
             if (em.isOpen())
                 em.close();
         }
     }
 }

