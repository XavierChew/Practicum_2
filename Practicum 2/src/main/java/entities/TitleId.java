package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * **TitleId** is a composite key class used by the Titles entity.
 *
 * * This class implements the {@link Serializable} interface and defines the structure
 * for the composite primary key of the **"titles"** table in the database.
 * * It combines the **employee number** (empNo), the **title name** (title), and the
 * **starting date of the title** (fromDate) to uniquely identify a specific title record.
 * This is necessary because an employee can hold the same title multiple times with
 * different start dates.
 * * **Important:** As required by the JPA specification for composite keys, this class must
 * override the {@code equals()} and {@code hashCode()} methods.
 *
 * @see Titles
 * @author Ilyas & Wei Xian
 */
public class TitleId implements Serializable {

    /**
     * The employee number, corresponding to the {@code emp_no} column.
     * This field must have the same type and name as the corresponding field in the
     * {@link Titles} entity.
     */
    private int empNo;      // primitive PK

    /**
     * The title name, corresponding to the {@code title} column.
     * This field must have the same type and name as the corresponding field in the
     * {@link Titles} entity.
     */
    private String title;

    /**
     * The starting date of the title record, corresponding to the {@code from_date} column.
     * This field, along with {@code empNo} and {@code title}, makes the primary key unique.
     */
    private LocalDate fromDate;

    /**
     * Default public constructor. Required by JPA specification for composite keys.
     */
    public TitleId() {}

    /**
     * Parameterized constructor to initialize the composite key fields.
     *
     * @param empNo The employee's unique number.
     * @param title The name of the title (e.g., "Senior Engineer").
     * @param fromDate The starting date of the title assignment.
     */
    public TitleId(int empNo, String title, LocalDate fromDate) {
        this.empNo = empNo;
        this.title = title;
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
     * Retrieves the title name component of the key.
     * @return The title name.
     */
    public String getTitle() { return title; }

    /**
     * Sets the title name component of the key.
     * @param title The title name to set.
     */
    public void setTitle(String title) { this.title = title; }

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
     * Equality is based on {@code empNo}, {@code title}, and {@code fromDate} being equal.
     *
     * @param o The object to compare with.
     * @return {@code true} if the objects are the same or have equal key fields, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleId titleId = (TitleId) o;
        return empNo == titleId.empNo &&
                Objects.equals(title, titleId.title) &&
                Objects.equals(fromDate, titleId.fromDate);
    }

    /**
     * Returns a hash code value for the object, supporting the hash key lookup requirement.
     * The hash code is generated based on {@code empNo}, {@code title}, and {@code fromDate}.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(empNo, title, fromDate);
    }
}