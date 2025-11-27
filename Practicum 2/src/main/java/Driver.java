import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import entities.Employee;

public class Driver {
    public static void main(String[] args) {
        final String DBName = "employees";

        Map<String,String> persistenceMap = new HashMap<>();
        persistenceMap.put("jakarta.persistence.jdbc.url", "jdbc:mariadb://localhost:3306/"+DBName);

        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService", persistenceMap);){
            EntityManager em = emf.createEntityManager();
            EmployeeServices employeeDAO = new EmployeeServices(em);
            Employee employee = employeeDAO.getEmployee(10001);
            System.out.println(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    }

