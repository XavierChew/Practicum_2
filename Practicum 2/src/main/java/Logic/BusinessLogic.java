package Logic;

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
//        EntityManager em = emf.createEntityManager();
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


 }

