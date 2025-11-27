package services;

import jakarta.persistence.EntityManager;

public class EntityManagerFactoryService {
    EntityManager em = emf.createEntityManager();
    EmployeeService service = new EmployeeService(em);

}
