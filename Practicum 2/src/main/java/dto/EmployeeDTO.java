package dto;

import java.time.LocalDate;

/**
 * **EmployeeDTO** is a **Data Transfer Object (DTO)** for conveying essential employee information.
 *
 * <p>It is used primarily to transfer a subset of data from the {@link entities.Employee Employee entity}
 * between the server and client, often to reduce payload size or hide sensitive fields
 * (like salary, birth date, or gender) that are not needed by the client.</p>
 *
 * * This class is a simple object containing only data fields, constructors, and accessor methods.
 * * It is **not persisted** to the database.
 * * @author Ilyas & Wei Xian
 *
 */
public class EmployeeDTO {

    /**
     * The unique employee number.
     */
    private int emp_no;

    /**
     * The employee's first name.
     */
    private String first_name;

    /**
     * The employee's last name.
     */
    private String last_name;

    /**
     * The employee's hire date.
     */
    private LocalDate hire_date;

    /**
     * Parameterized constructor for creating an {@code EmployeeDTO} instance.
     *
     * @param emp_no The employee number.
     * @param first_name The employee's first name.
     * @param last_name The employee's last name.
     * @param hire_date The employee's hire date.
     */
    public EmployeeDTO(int emp_no, String first_name, String last_name, LocalDate hire_date) {
        this.emp_no = emp_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hire_date = hire_date;
    }

    /**
     * Retrieves the employee number.
     * @return The employee number.
     */
    public int getEmp_no() {
        return emp_no;
    }

    /**
     * Retrieves the employee's first name.
     * @return The first name.
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Retrieves the employee's last name.
     * @return The last name.
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Retrieves the employee's hire date.
     * @return The hire date.
     */
    public LocalDate getHire_date() {
        return hire_date;
    }

    /**
     * Sets the employee number.
     * @param emp_no The employee number to set.
     */
    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    /**
     * Sets the employee's first name.
     * @param first_name The first name to set.
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Sets the employee's last name.
     * @param last_name The last name to set.
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Sets the employee's hire date.
     * @param hire_date The hire date to set.
     */
    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }
}