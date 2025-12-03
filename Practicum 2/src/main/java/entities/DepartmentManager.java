package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * **DepartmentManager** is a JPA entity representing the assignment of an employee as a manager
 * of a specific department over a period of time.
 *
 * * It is mapped to the **"dept_manager"** table and uses a composite primary key defined by {@link DepartmentManagerId}.
 * * This entity manages the many-to-many relationship between the {@link Employee} and {@link Department} entities,
 * capturing the historical record of managerial appointments with start and end dates.
 *
 * @author Ilyas & Wei Xian
 * @see DepartmentManagerId
 */
@Entity
@IdClass(DepartmentManagerId.class)
@Table(name = "dept_manager")
public class DepartmentManager {

    /**
     * Part of the composite primary key. The unique identifier of the department being managed.
     * Maps to the column **"dept_no"** in the "dept_manager" table.
     */
    @Id
    @Column(name = "dept_no")
    private String deptNo;

    /**
     * Part of the composite primary key. The unique identifier of the employee who is the manager.
     * Maps to the column **"emp_no"** in the "dept_manager" table.
     * * This field is ignored during JSON serialization to prevent redundancy, as the employee relationship is also present.
     */
    @Id
    @Column(name = "emp_no")
    @JsonIgnore
    private int empNo;

    /**
     * The associated {@link Department} entity that this employee manages.
     * This forms a **Many-to-One** relationship.
     * The join column is {@code dept_no}, which is neither insertable nor updatable
     * as it is part of the composite key defined locally.
     * {@code @JsonIgnoreProperties} is used to ignore Hibernate-specific fields during serialization.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Department department;

    /**
     * The associated {@link Employee} entity who holds the manager position.
     * This forms a **Many-to-One** relationship.
     * The join column is {@code emp_no}, which is neither insertable nor updatable.
     * {@code @JsonBackReference} is used to prevent infinite recursion during JSON serialization.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    @JsonBackReference
    private Employee employee;

    /**
     * The date the employee started their managerial position in this department.
     * Maps to the column **"from_date"**.
     */
    @Column(name = "from_date")
    private LocalDate fromDate;

    /**
     * The date the employee ended their managerial position in this department.
     * Maps to the column **"to_date"**.
     * A value of '9999-01-01' often signifies the current assignment.
     */
    @Column(name = "to_date")
    private LocalDate toDate;

    /**
     * Default public constructor required by JPA.
     */
    public DepartmentManager() {}

    /**
     * Parameterized constructor for creating a new, current department manager assignment.
     * The {@code toDate} is automatically set to '9999-01-01' to indicate the current assignment.
     *
     * @param deptNo The department's number.
     * @param empNo The manager's employee number.
     * @param fromDate The starting date of the managerial assignment.
     */
    public DepartmentManager(String deptNo, int empNo, LocalDate fromDate) {
        this.deptNo = deptNo;
        this.empNo = empNo;
        this.fromDate = fromDate;
        this.toDate = LocalDate.of(9999, 1, 1);
    }

    /**
     * Retrieves the associated {@link Department} entity.
     * @return The {@link Department} entity.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets the associated {@link Department} entity.
     * @param department The {@link Department} entity to set.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Retrieves the associated {@link Employee} entity (the manager).
     * @return The {@link Employee} entity.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the associated {@link Employee} entity (the manager).
     * @param employee The {@link Employee} entity to set.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Retrieves the start date of the manager's assignment in the department.
     * @return The start date as a {@link LocalDate}.
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Sets the start date of the manager's assignment in the department.
     * @param fromDate The start date to set.
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Retrieves the end date of the manager's assignment in the department.
     * @return The end date as a {@link LocalDate}.
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Sets the end date of the manager's assignment in the department.
     * @param toDate The end date to set.
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}