package Logic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import services.EmployeeService;

import java.util.HashMap;
import java.util.Map;

public class EntityManagerFactory {
    static final String DBNAME = "employees";

    public EntityManagerFactory() {
        Map<String,String> persistenceMap = new HashMap<>();
        persistenceMap.put("jakarta.persistence.jdbc.url",
                "jdbc:mariadb://localhost:3306/"+DBNAME);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService", persistenceMap);
    }



    EntityManager em = emf.createEntityManager();
    EmployeeService service = new EmployeeService(em);

}
