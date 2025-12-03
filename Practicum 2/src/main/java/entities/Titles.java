package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 *  **Titles** is a JPA entity that represents an employee's job title for a specific period.
 *
 * * It is mapped to the **"titles"** table and uses a composite primary key defined by {@link TitleId}.
 * * This entity allows for tracking the historical career progression and job titles held by an employee over time.
 *
 * ## Named Queries
 * The following named queries are defined for efficient data access:
 * 1.  **{@code Titles.updateTitleDate}**: Updates the {@code toDate} for a specific employee's title record.
 * 2.  **{@code Titles.findLatestTitle}**: Retrieves the current (latest) title record for a specific employee by checking for a maximum {@code toDate} value (often '9999-01-01').
 *
 * @author Ilyas & Wei Xian
 * @see TitleId
 * @version 1.0
 */
@Entity
@Table(name = "titles")
@IdClass(TitleId.class)
@NamedQueries({
        @NamedQuery(name = "Titles.updateTitleDate", query = "UPDATE Titles t SET t.toDate = :toDate WHERE t.empNo = :emp_no AND t.toDate = :date"),
        @NamedQuery(name = "Titles.findLatestTitle", query = "SELECT t from Titles t WHERE t.empNo = :emp_no AND t.toDate = :date")
})
public class Titles {

    /**
     * Part of the composite primary key. The unique identifier of the employee.
     * Maps to the column "emp_no" in the "titles" table.
     * This field is ignored during JSON serialization to prevent redundancy, as the employee relationship is also present.
     */
    @Id
    @Column(name= "emp_no")
    @JsonIgnore
    private int empNo;

    /**
     * The associated {@link Employee} entity.
     * This forms a Many-to-One relationship.
     * The join column is {@code emp_no}, which is neither insertable nor updatable
     * as it is part of the composite key defined locally.
     * {@code @JsonBackReference} is used to prevent infinite recursion during JSON serialization.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "emp_no", insertable = false, updatable = false)
    @JsonBackReference
    private Employee employee;

    /**
     * Part of the composite primary key. The name of the title held by the employee.
     * This field is used to differentiate title records for the same employee.
     */
    @Id
    private String title;

    /**
     * Part of the composite primary key. The start date for which this title is valid.
     * Maps to the column "from_date".
     */
    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    /**
     * The end date for which this title is valid.
     * Maps to the column "to_date".
     * A value of '9999-01-01' often signifies the current, active title.
     */
    @Column(name = "to_date")
    private LocalDate toDate;

    /**
     * Default public constructor required by JPA.
     */
    public Titles(){}

    /**
     * Parameterized constructor for creating a new, current title record.
     * The {@code toDate} is automatically set to '9999-01-01' to indicate the current title.
     *
     * @param empNo The employee's number.
     * @param title The title name.
     * @param fromDate The starting date of the title assignment.
     */
    public Titles(int empNo, String title, LocalDate fromDate) {
        this.empNo = empNo;
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = LocalDate.of(9999, 1, 1);
    }

    // getters and setters
    /**
     * Retrieves the employee number (part of the composite key).
     * @return The employee number.
     */
    public int getEmpNo() { return empNo; }

    /**
     * Sets the employee number (part of the composite key).
     * @param empNo The employee number to set.
     */
    public void setEmpNo(int empNo) { this.empNo = empNo; }

    /**
     * Retrieves the associated {@link Employee} entity.
     * @return The {@link Employee} entity.
     */
    public Employee getEmployee() { return employee; }

    /**
     * Sets the associated {@link Employee} entity.
     * @param employee The {@link Employee} entity to set.
     */
    public void setEmployee(Employee employee) { this.employee = employee; }

    /**
     * Retrieves the title name (part of the composite key).
     * @return The title name string.
     */
    public String getTitle() { return title; }

    /**
     * Sets the title name (part of the composite key).
     * @param title The title name string to set.
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Retrieves the start date for the title record.
     * @return The start date as a {@link LocalDate}.
     */
    public LocalDate getFromDate() { return fromDate; }

    /**
     * Sets the start date for the title record.
     * @param fromDate The start date to set.
     */
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    /**
     * Retrieves the end date for the title record.
     * @return The end date as a {@link LocalDate}.
     */
    public LocalDate getToDate() { return toDate; }

    /**
     * Sets the end date for the title record.
     * @param toDate The end date to set.
     */
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }

    /**
     * Returns a string representation of the {@code Titles} object.
     *
     * @return A string containing key title details.
     */
    @Override
    public String toString() {
        return "Titles{" +
                "empNo=" + empNo +
                ", title='" + title + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}