package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * **DepartmentManagerId** is a composite key class used by the {@link DepartmentManager} entity.
 *
 * * This class implements the {@link Serializable} interface and defines the structure
 * for the composite primary key of the **"dept_manager"** table in the database.
 * * It combines the **department number** (deptNo) and the **employee number** (empNo)
 * to uniquely identify a specific manager assignment record.
 * * **Important:** As required by the JPA specification for composite keys, this class must
 * override the {@code equals()} and {@code hashCode()} methods.
 *
 * @see DepartmentManager
 * @author Ilyas & Wei Xian
 */
public class DepartmentManagerId implements Serializable {

    /**
     * The department number, corresponding to the {@code dept_no} column.
     * This field must have the same type and name as the corresponding field in the
     * {@link DepartmentManager} entity.
     */
    private String deptNo;

    /**
     * The employee number, corresponding to the {@code emp_no} column.
     * This field must have the same type and name as the corresponding field in the
     * {@link DepartmentManager} entity.
     */
    private int empNo;

    /**
     * Default public constructor. Required by JPA specification for composite keys.
     */
    public DepartmentManagerId() {}

    /**
     * Parameterized constructor to initialize the composite key fields.
     *
     * @param deptNo The department's unique number.
     * @param empNo The employee's unique number.
     */
    public DepartmentManagerId(String deptNo, int empNo) {
        this.deptNo = deptNo;
        this.empNo = empNo;
    }

    // getters and setters
    /**
     * Retrieves the department number component of the key.
     * @return The department number.
     */
    public String getDeptNo() { return deptNo; }

    /**
     * Sets the department number component of the key.
     * @param deptNo The department number to set.
     */
    public void setDeptNo(String deptNo) { this.deptNo = deptNo; }

    /**
     * Retrieves the employee number component of the key.
     * @return The employee number.
     */
    public int getEmpNo() { return empNo; }

    /**
     * Sets the employee number component of the key.
     * @param empNo The employee number to set.
     */
    public void setEmpNo(int empNo) { this.empNo = empNo; }

    /**
     * Compares this composite key object with the specified object for equality.
     * Equality is based on both {@code deptNo} and {@code empNo} being equal.
     *
     * @param o The object to compare with.
     * @return {@code true} if the objects are the same or have equal key fields, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentManagerId)) return false;
        DepartmentManagerId that = (DepartmentManagerId) o;
        return empNo == that.empNo && Objects.equals(deptNo, that.deptNo);
    }

    /**
     * Returns a hash code value for the object, supporting the hash key lookup requirement.
     * The hash code is generated based on both {@code deptNo} and {@code empNo}.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(deptNo, empNo);
    }
}