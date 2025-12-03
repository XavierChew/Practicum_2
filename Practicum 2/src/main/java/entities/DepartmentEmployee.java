package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * DepartmentEmployee is an entity that represents a department employee in the database.
 * It is used to store information about a department employee.
 * It is mapped to the dept_emp table and uses a composite primary key defined by {@link DepartmentEmployeeId}
 * It is persisted to the database.
 *
 * @author Ilyas & Wei Xian
 * @see DepartmentEmployeeId
 */
@Entity
@IdClass(DepartmentEmployeeId.class)
@Table(name = "dept_emp")
@NamedQueries({
        @NamedQuery(name = "DepartmentEmployee.updateDepartmentDate", query = "UPDATE DepartmentEmployee de SET de.toDate = :toDate WHERE de.empNo = :emp_no AND de.toDate = :date"),
        @NamedQuery(name = "DepartmentEmployee.getLatestDepartment", query = "SELECT de FROM DepartmentEmployee de WHERE de.empNo = :emp_no AND de.toDate = :date")
})
public class DepartmentEmployee {

    /**
     * Part of the composite primary key. The unique identifier of the employee.
     * Maps to the column **"emp_no"** in the "dept_emp" table.
     */
    @Id
    @Column(name = "emp_no")
    private int empNo;

    /**
     * Part of the composite primary key. The unique identifier of the department.
     * Maps to the column **"dept_no"** in the "dept_emp" table.
     */
    @Id
    @Column(name = "dept_no")
    private String deptNo;

    /**
     * The associated {@link Employee} entity.
     * This forms a Many-to-One relationship.
     * The join column is {@code emp_no}, which is neither insertable nor updatable
     * as it is part of the composite key defined locally.
     * {@code @JsonBackReference} is used to prevent infinite recursion during JSON serialization.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    @JsonBackReference // avoids recursion
    private Employee employee;

    /**
     * The associated {@link Department} entity.
     * This forms a Many-to-One relationship.
     * The join column is {@code dept_no}, which is neither insertable nor updatable.
     * {@code @JsonIgnoreProperties} is used to ignore Hibernate-specific fields during serialization.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Department department;

    /**
     * The date the employee started working in this department.
     * Maps to the column "from_date".
     */
    @Column(name = "from_date")
    private LocalDate fromDate;

    /**
     * The date the employee stopped working in this department.
     * Maps to the column "to_date".
     */
    @Column(name = "to_date")
    private LocalDate toDate;

    /**
     * Default public constructor required by JPA.
     */
    public DepartmentEmployee() {
    }

    /**
     * Constructor for creating a new, current department-employee assignment.
     * The {@code toDate} is automatically set to '9999-01-01' to indicate the current assignment.
     *
     * @param empNo The employee's number.
     * @param deptNo The department's number.
     * @param fromDate The starting date of the assignment.
     */
    public DepartmentEmployee(int empNo, String deptNo, LocalDate fromDate) {
        this.empNo = empNo;
        this.deptNo = deptNo;
        this.fromDate = fromDate;
        this.toDate = LocalDate.of(9999, 1, 1);
    }


    /**
     * Sets the associated {@link Employee} entity.
     * @param employee The {@link Employee} entity to set.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Retrieves the department number (part of the composite key).
     * @return The department number string.
     */
    public String getDeptNo() {
        return deptNo;
    }

    /**
     * Sets the department number (part of the composite key).
     * @param deptNo The department number string to set.
     */
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
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
     * Retrieves the start date of the employee's assignment in the department.
     * @return The start date as a {@link LocalDate}.
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Sets the start date of the employee's assignment in the department.
     * @param fromDate The start date to set.
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Retrieves the end date of the employee's assignment in the department.
     * @return The end date as a {@link LocalDate}.
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Sets the end date of the employee's assignment in the department.
     * @param toDate The end date to set.
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * Returns a string representation of the {@code DepartmentEmployee} object.
     * Note: The original {@code toString()} implementation attempts to access
     * the employee and department entities; ensure they are initialized
     * (not proxies) before calling this method, or use local fields for safety.
     *
     * @return A string containing the employee number, department number, from date, and to date.
     */
    @Override
    public String toString() {
        // Updated to use the local empNo and deptNo fields for safer proxy access,
        // as the associated entities might be lazily loaded (proxies).
        return "DepartmentEmployee{" +
                "emp_no=" + empNo +
                ", dept_no='" + deptNo + '\'' +
                ", from_date=" + fromDate +
                ", to_date=" + toDate +
                '}';
    }
}