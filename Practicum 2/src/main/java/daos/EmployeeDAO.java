package daos;

import entities.Employee;
import jakarta.persistence.EntityManager;

public class EmployeeDAO {
    private EntityManager em;
    public EmployeeDAO(EntityManager em) {
        this.em = em;
    }

    public Employee getEmployee(int emp_no) {
        return em.find(Employee.class, emp_no);
    }
}
