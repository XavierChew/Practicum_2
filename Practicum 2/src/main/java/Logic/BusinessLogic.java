package Logic;

 import jakarta.persistence.EntityManager;
 import jakarta.persistence.Persistence;import services.EmployeeService;
import entities.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import services.EmployeeService;
import services.DepartmentService;
 import java.util.HashMap;
 import java.util.Map;

 public class  BusinessLogic {

     private static final String DBNAME = "employees";
     private static EntityManagerFactory emf = null;

     private BusinessLogic(){
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
 }

//@ApplicationPath("/api")
//public class BusinessLogic extends Application {
//
//    private EntityManager EntityManagerFactoryService;
//    private final EntityManagerFactory emf = EntityManagerFactoryService.getEntityManagerFactory();
//
//    public List<Department> findAllDepartment(){
//        EntityManager em = emf.createEntityManager();
//        List<Department> departments = em.createNamedQuery("Department.findAll", Department.class).getResultList();
//
//        return null;
//    }
//        @Override
//        public Set<Class<?>> getClasses() {
//            Set<Class<?>> classes = new HashSet<>();
//            classes.add(EmployeeService.class);
//            classes.add(DepartmentService.class);
//            return classes;
//        }
//}