package entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/**
 * Department is an entity that represents a department in the database.
 * It is used to store information about a department.
 * It is persisted to the database.
 * @author Ilyas & Wei Xian
 */
@Entity
@Table(name = "departments")
public class Department {

    /**
     * The unique identifier for the department.
     * Maps to the primary key column "dept_no" in the "departments" table.
     */
    @Id
    @Column(name = "dept_no")
    private String deptNo;

    /**
     * The official name of the department.
     * Maps to the column "dept_name" in the "departments" table.
     */
    @Column(name = "dept_name")
    private String deptName;


    //Mapping to other entities
    /**
     * A collection of {@link DepartmentEmployee} records associated with this department.
     * This establishes a One-to-Many relationship.
     * The relationship is fetched lazily and mapped by the "department" field in the
     * {@code DepartmentEmployee} entity.
     * This field is ignored during JSON serialization.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private List<DepartmentEmployee> departmentEmployees;

    /**
     * A collection of {@link DepartmentManager} records associated with this department.
     * This establishes a One-to-Many relationship.
     * The relationship is fetched lazily and mapped by the "department" field in the
     * {@code DepartmentEmployee} entity.
     * This field is ignored during JSON serialization.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private List<DepartmentManager> departmentManagers;

    /**
     * Public no-args constructor required by JPA
     */
    public Department (){}

    // Getter and Setter
    /**
     *Retrieves unique department number
     * @return the department number.
     */

    public String getDeptNo() { return deptNo; }

    /**
     *Sets unique department number
     * @param deptNo the department number.
     */
    public void setDeptNo(String deptNo) { this.deptNo = deptNo; }

    /**
     *Retrieves unique department number
     * @return the department number.
     */
    public String getDeptName() { return deptName; }

    /**
     *Set unique department name
     * @param deptName the department name.
     */
    public void setDeptName(String deptName) { this.deptName = deptName; }

    /**
     *Retrieves the lists of department-to-employees association
     * @return A list of {@link DepartmentEmployee} objects.
     */
    public List<DepartmentEmployee> getDepartmentEmployees() { return departmentEmployees; }


    /**
     * Sets the list of employee-to-department associations.
     * @param departmentEmployees The list of {@link DepartmentEmployee} objects to set.
     */
    public void setDepartmentEmployees(List<DepartmentEmployee> departmentEmployees)
    { this.departmentEmployees = departmentEmployees; }

    /**
     * Retrieves the list of manager-to-department associations.
     * @return A list of {@link DepartmentManager} objects.
     */
    public List<DepartmentManager> getDepartmentManagers() { return departmentManagers; }

    /**
     * Sets the list of manager-to-department associations.
     * @param departmentManagers The list of {@link DepartmentManager} objects to set.
     */
    public void setDepartmentManagers(List<DepartmentManager> departmentManagers)
    { this.departmentManagers = departmentManagers; }

    /**
     * Returns a string representation of the {@code Department} object.
     * @return A string containing the department number and department name.
     */
    @Override
    public String toString() {
        return "Department [deptNo=" + deptNo + ", deptName=" + deptName + "]";
    }
}