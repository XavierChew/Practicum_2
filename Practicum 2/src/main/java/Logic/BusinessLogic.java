package Logic;

 import entities.DepartmentEmployee;
 import entities.DepartmentManager;
 import entities.Employee;
 import jakarta.persistence.*;
 import services.EmployeeService;
import entities.Department;
import jakarta.persistence.EntityManager;
 import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import services.EmployeeService;
import services.DepartmentService;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.ArrayList;
 import dto.EmployeeDTO;

 public class  BusinessLogic {

     private static final String DBNAME = "employees";
     private static EntityManagerFactory emf = null;
     @PersistenceContext
     private static EntityManager em = null;

     public BusinessLogic(){
         emf = getEntityManagerFactory();
         em = emf.createEntityManager();
     }

     public static EntityManagerFactory getEntityManagerFactory() {

         if (emf == null) {
             Map<String, String> persistenceMap = new HashMap<>();
             persistenceMap.put("jakarta.persistence.jdbc.url",
                     "jdbc:mariadb://localhost:3306/" + DBNAME);
             try {
                 emf =  Persistence.createEntityManagerFactory("EmployeeService", persistenceMap);

             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
         }
         return emf;
     }

     public static void closeEntityManagerFactory () {
         if (emf != null && emf.isOpen()) {
             emf.close();
             emf = null;
         }
     }

     public List<Department> findAllDepartment(){
        EntityManager em = emf.createEntityManager();
        List<Department> departments = new ArrayList<>();

        try {
           departments =  em.createQuery("SELECT d from Department d", Department.class).getResultList();

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





 }

