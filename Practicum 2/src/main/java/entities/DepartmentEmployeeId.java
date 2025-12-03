package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *  DepartmentEmployeeId is a composite key class used by the {@link DepartmentEmployee} entity.
 *
 * This class implements the {@link Serializable} interface and defines the structure
 * for the composite primary key of the "dept_emp" table in the database.
 *  It combines the employee number (empNo) and the department number (deptNo)
 * to uniquely identify a specific assignment record.
 * <b>Important:</b> According to JPA specification for composite keys, this class must
 * override {@code equals()} and {@code hashCode()}.
 *
 * @see DepartmentEmployee
 * @author Ilyas & Wei Xian
 */
public class DepartmentEmployeeId implements Serializable {

    /**
     * The employee number, corresponding to the {@code emp_no} column.
     * This field must have the same type and name as the corresponding field in the
     * {@link DepartmentEmployee} entity.
     */
    private int empNo;

    /**
     * The department number, corresponding to the {@code dept_no} column.
     * This field must have the same type and name as the corresponding field in the
     * {@link DepartmentEmployee} entity.
     */
    private String deptNo;

    /**
     * Default public constructor. Required by JPA specification for composite keys.
     */
    public DepartmentEmployeeId() {}

    /**
     * Parameterized constructor to initialize the composite key fields.
     *
     * @param empNo The employee's unique number.
     * @param deptNo The department's unique number.
     */
    public DepartmentEmployeeId(int empNo, String deptNo) {
        this.empNo = empNo;
        this.deptNo = deptNo;
    }

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
     * Compares this composite key object with the specified object for equality.
     * Equality is based on both {@code empNo} and {@code deptNo} being equal.
     *
     * @param o The object to compare with.
     * @return {@code true} if the objects are the same or have equal key fields, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentEmployeeId)) return false;
        DepartmentEmployeeId that = (DepartmentEmployeeId) o;
        return empNo == that.empNo && Objects.equals(deptNo, that.deptNo);
    }

    /**
     * Returns a hash code value for the object, supporting the hash key lookup requirement.
     * The hash code is generated based on both {@code empNo} and {@code deptNo}.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(empNo, deptNo);
    }
}