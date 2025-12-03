package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * **Salaries** is a JPA entity that represents an employee's salary record for a specific period.
 *
 * * It is mapped to the **"salaries"** table and uses a composite primary key defined by {@link SalaryId}.
 * * This entity allows for tracking the historical changes in an employee's compensation over time.
 *
 * ## Named Queries
 * The following named queries are defined for efficient data access:
 * 1.  **{@code Salaries.updateSalaryDate}**: Updates the {@code toDate} for a specific employee's salary record.
 * 2.  **{@code Salaries.findLatestSalary}**: Retrieves the current (latest) salary record for a specific employee by checking for a maximum {@code toDate} value (often '9999-01-01').
 *
 * @author Ilyas & Wei Xian
 * @see SalaryId
 */
@Entity
@IdClass(SalaryId.class)
@Table(name = "salaries")
@NamedQueries({
        @NamedQuery(name = "Salaries.updateSalaryDate", query = "UPDATE Salaries s SET s.toDate = :toDate WHERE s.empNo = :emp_no AND s.toDate = :date"),
        @NamedQuery(name = "Salaries.findLatestSalary", query = "SELECT s FROM Salaries s WHERE s.empNo = :emp_no AND s.toDate = :date")
})
public class Salaries {

    /**
     * Part of the composite primary key. The unique identifier of the employee.
     * Maps to the column **"emp_no"** in the "salaries" table.
     * * This field is ignored during JSON serialization to prevent redundancy, as the employee relationship is also present.
     */
    @Id
    @Column(name = "emp_no")
    @JsonIgnore
    private int empNo;

    /**
     * The associated {@link Employee} entity.
     * This forms a **Many-to-One** relationship.
     * The join column is {@code emp_no}, which is neither insertable nor updatable
     * as it is part of the composite key defined locally.
     * {@code @JsonBackReference} is used to prevent infinite recursion during JSON serialization.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    @JsonBackReference
    private Employee employee;

    /**
     * The salary amount for the period.
     */
    private int salary;

    /**
     * Part of the composite primary key. The start date for which this salary is valid.
     * Maps to the column **"from_date"**.
     */
    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    /**
     * The end date for which this salary is valid.
     * Maps to the column **"to_date"**.
     * A value of '9999-01-01' often signifies the current, active salary.
     */
    @Column(name = "to_date")
    private LocalDate toDate;

    /**
     * Default public constructor required by JPA.
     */
    public Salaries() {};

    /**
     * Parameterized constructor for creating a new, current salary record.
     * The {@code toDate} is automatically set to '9999-01-01' to indicate the current salary.
     *
     * @param empNo The employee's number.
     * @param salary The salary amount.
     * @param fromDate The starting date of the salary.
     */
    public Salaries(int empNo, int salary, LocalDate fromDate) {
        this.empNo = empNo;
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = LocalDate.of(9999, 1, 1);
    }

    /**
     * Retrieves the employee number (part of the composite key).
     * @return The employee number.
     */
    public int getEmpNo() {return empNo;}

    /**
     * Sets the employee number (part of the composite key).
     * @param empNo The employee number to set.
     */
    public void setEmpNo(int empNo) {this.empNo = empNo;}

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
     * Retrieves the salary amount.
     * @return The salary as an integer.
     */
    public int getSalary() { return salary; }

    /**
     * Sets the salary amount.
     * @param salary The salary amount to set.
     */
    public void setSalary(int salary) { this.salary = salary; }

    /**
     * Retrieves the start date for the salary record.
     * @return The start date as a {@link LocalDate}.
     */
    public LocalDate getFromDate() { return fromDate; }

    /**
     * Sets the start date for the salary record.
     * @param fromDate The start date to set.
     */
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    /**
     * Retrieves the end date for the salary record.
     * @return The end date as a {@link LocalDate}.
     */
    public LocalDate getToDate() { return toDate; }

    /**
     * Sets the end date for the salary record.
     * @param toDate The end date to set.
     */
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }

    /**
     * Returns a string representation of the {@code Salaries} object.
     * Note: Accessing the {@code employee} field in this method may trigger a lazy load.
     *
     * @return A string containing key salary details.
     */
    @Override
    public String toString() {
        return "Salaries{" +
                "empNo=" + empNo +
                ", employee=" + employee +
                ", salary=" + salary +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}