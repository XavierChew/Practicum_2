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

 public class  BusinessLogic {

     private static final String DBNAME = "employees";
     private static EntityManagerFactory emf = null;
     @PersistenceContext
     private static EntityManager em = null;

     public BusinessLogic() {
         emf = getEntityManagerFactory();
         em = emf.createEntityManager();
     }

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

     public static void closeEntityManagerFactory() {
         if (emf != null && emf.isOpen()) {
             emf.close();
             emf = null;
         }
     }

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

                 // Initialize nested lazy proxies
                 for (DepartmentEmployee de : emp.getDeptEmpList()) {
                     de.getDepartment().getDeptName(); // access at least one property
                 }
                 // Initialize nested Department in DepartmentManager
                 for (DepartmentManager dm : emp.getDeptManagerList()) {
                     dm.getDepartment().getDeptName(); // access a real property
                 }
             }
             return emp;
         } finally {
             em.close();
         }
     }

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
             System.out.println("Title update:");
             em.createNamedQuery("Titles.updateTitleDate")
                     .setParameter("toDate", LocalDate.now())
                     .setParameter("emp_no", promotion.getEmpNo())
                     .setParameter("date", toPresentDate)
                     .executeUpdate();
             Titles title = new Titles(promotion.getEmpNo(), promotion.getTitle(), LocalDate.now());
             em.persist(title);

             System.out.println("Salary update:");
             em.createNamedQuery("Salaries.updateSalaryDate")
                     .setParameter("toDate", LocalDate.now())
                     .setParameter("emp_no", promotion.getEmpNo())
                     .setParameter("date", toPresentDate)
                     .executeUpdate();
             Salaries salary = new Salaries(promotion.getEmpNo(), promotion.getSalary(), LocalDate.now());
             em.persist(salary);

             System.out.println("Department update:");
             em.createNamedQuery("DepartmentEmployee.updateDepartmentDate")
                     .setParameter("toDate", LocalDate.now())
                     .setParameter("emp_no", promotion.getEmpNo())
                     .setParameter("date", toPresentDate)
                     .executeUpdate();
             DepartmentEmployee deptEmp = new DepartmentEmployee(promotion.getEmpNo(), promotion.getDepartmentID(), LocalDate.now());
             em.persist(deptEmp);

             if (promotion.getTitle().equals("manager")) {
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

