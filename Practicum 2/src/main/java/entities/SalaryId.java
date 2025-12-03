package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * **SalaryId** is a composite key class used by the {@link Salaries} entity.
 *
 * * This class implements the {@link Serializable} interface and defines the structure
 * for the composite primary key of the **"salaries"** table in the database.
 * * It combines the **employee number** (empNo) and the **starting date of the salary** (fromDate)
 * to uniquely identify a specific salary record, as an employee can have multiple salaries over time,
 * but only one starting on a particular date.
 * * **Important:** As required by the JPA specification for composite keys, this class must
 * override the {@code equals()} and {@code hashCode()} methods.
 *
 * @see Salaries
 * @author Ilyas & Wei Xian
 * @version 1.0
 */
public class SalaryId implements Serializable {

    /**
     * The employee number, corresponding to the {@code emp_no} column.
     * This field must have the same type and name as the corresponding field in the
     * {@link Salaries} entity.
     */
    private int empNo;        // use primitive PK field, not Employee

    /**
     * The starting date of the salary record, corresponding to the {@code from_date} column.
     * This field, along with {@code empNo}, makes the primary key unique.
     */
    private LocalDate fromDate;


    /**
     * Default public constructor. Required by JPA specification for composite keys.
     */
    public SalaryId() {}

    /**
     * Parameterized constructor to initialize the composite key fields.
     *
     * @param empNo The employee's unique number.
     * @param fromDate The starting date of the salary.
     */
    public SalaryId(int empNo, LocalDate fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }

    // getters & setters
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
     * Retrieves the start date component of the key.
     * @return The start date.
     */
    public LocalDate getFromDate() { return fromDate; }

    /**
     * Sets the start date component of the key.
     * @param fromDate The start date to set.
     */
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    /**
     * Compares this composite key object with the specified object for equality.
     * Equality is based on both {@code empNo} and {@code fromDate} being equal.
     *
     * @param o The object to compare with.
     * @return {@code true} if the objects are the same or have equal key fields, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryId salaryId = (SalaryId) o;
        return empNo == salaryId.empNo && Objects.equals(fromDate, salaryId.fromDate);
    }

    /**
     * Returns a hash code value for the object, supporting the hash key lookup requirement.
     * The hash code is generated based on both {@code empNo} and {@code fromDate}.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(empNo, fromDate);
    }
}