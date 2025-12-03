package entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * **Employee** is a JPA entity that represents a single employee record in the database.
 *
 * * It is mapped to the **"employees"** table and serves as the core entity for personnel data.
 * * It stores basic biographical information and maintains **One-to-Many** relationships with all
 * associated history tables: titles, salaries, department assignments, and manager assignments.
 *
 * @author Ilyas & Wei Xian
 *
 */
@Entity
@Table(name = "employees")
public class Employee {

    /**
     * The unique employee number, serving as the primary key.
     * Maps to the column **"emp_no"** in the "employees" table.
     */
    @Id
    @Column(name = "emp_no")
    private int emp_no;

    /**
     * The employee's date of birth.
     * Maps to the column **"birth_date"**.
     */
    @Column(name = "birth_date")
    private LocalDate birth_date;

    /**
     * The employee's first name.
     * Maps to the column **"first_name"**.
     */
    @Column(name = "first_name")
    private String first_name;

    /**
     * The employee's last name.
     * Maps to the column **"last_name"**.
     */
    @Column(name = "last_name")
    private String last_name;

    /**
     * The employee's gender (e.g., 'M' or 'F').
     * Maps to the column **"gender"**.
     */
    @Column(name = "gender")
    private String gender;

    /**
     * The date the employee was hired.
     * Maps to the column **"hire_date"**.
     */
    @Column(name = "hire_date")
    private LocalDate hire_date;

    /**
     * A list of {@link Titles} records associated with this employee.
     * This **One-to-Many** relationship is fetched lazily and ordered by {@code toDate} ascending.
     * {@code @JsonManagedReference} indicates this side manages the JSON serialization.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @JsonManagedReference
    @OrderBy("toDate ASC")
    private List<Titles> titleList;

    /**
     * A list of {@link Salaries} records associated with this employee.
     * This **One-to-Many** relationship is fetched lazily and ordered by {@code toDate} ascending.
     * {@code @JsonManagedReference} indicates this side manages the JSON serialization.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @JsonManagedReference
    @OrderBy("toDate ASC")
    private List<Salaries> salaryList;

    /**
     * A list of {@link DepartmentEmployee} records (department assignments) associated with this employee.
     * This **One-to-Many** relationship is fetched lazily and ordered by {@code toDate} ascending.
     * {@code @JsonManagedReference} indicates this side manages the JSON serialization.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @JsonManagedReference
    @OrderBy("toDate ASC")
    private List<DepartmentEmployee> deptEmpList;

    /**
     * A list of {@link DepartmentManager} records (manager assignments) associated with this employee.
     * This **One-to-Many** relationship is fetched lazily and ordered by {@code toDate} ascending.
     * {@code @JsonManagedReference} indicates this side manages the JSON serialization.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @JsonManagedReference
    @OrderBy("toDate ASC")
    private List<DepartmentManager> deptManagerList;

    /**
     * Default public constructor required by JPA.
     */
    public Employee() {}

    // getters/setters

    /**
     * Retrieves the employee number.
     * @return The employee number.
     */
    public int getEmp_no() { return emp_no; }

    /**
     * Sets the employee number.
     * @param emp_no The employee number to set.
     */
    public void setEmp_no(int emp_no) { this.emp_no = emp_no; }

    /**
     * Retrieves the employee's date of birth.
     * @return The date of birth.
     */
    public LocalDate getBirth_date() { return birth_date; }

    /**
     * Sets the employee's date of birth.
     * @param birth_date The date of birth to set.
     */
    public void setBirth_date(LocalDate birth_date) { this.birth_date = birth_date; }

    /**
     * Retrieves the employee's first name.
     * @return The first name.
     */
    public String getFirst_name() { return first_name; }

    /**
     * Sets the employee's first name.
     * @param first_name The first name to set.
     */
    public void setFirst_name(String first_name) { this.first_name = first_name; }

    /**
     * Retrieves the employee's last name.
     * @return The last name.
     */
    public String getLast_name() { return last_name; }

    /**
     * Sets the employee's last name.
     * @param last_name The last name to set.
     */
    public void setLast_name(String last_name) { this.last_name = last_name; }

    /**
     * Retrieves the employee's gender.
     * @return The gender string.
     */
    public String getGender() { return gender; }

    /**
     * Sets the employee's gender.
     * @param gender The gender string to set.
     */
    public void setGender(String gender) { this.gender = gender; }

    /**
     * Retrieves the employee's hire date.
     * @return The hire date.
     */
    public LocalDate getHire_date() { return hire_date; }

    /**
     * Sets the employee's hire date.
     * @param hire_date The hire date to set.
     */
    public void setHire_date(LocalDate hire_date) { this.hire_date = hire_date; }

    /**
     * Retrieves the list of titles held by the employee, ordered chronologically by end date.
     * @return A list of {@link Titles} objects.
     */
    public List<Titles> getTitleList() { return titleList; }

    /**
     * Retrieves the list of salaries received by the employee, ordered chronologically by end date.
     * @return A list of {@link Salaries} objects.
     */
    public List<Salaries> getSalaryList() { return salaryList; }

    /**
     * Retrieves the list of department assignments for the employee, ordered chronologically by end date.
     * @return A list of {@link DepartmentEmployee} objects.
     */
    public List<DepartmentEmployee> getDeptEmpList() { return deptEmpList; }

    /**
     * Retrieves the list of managerial assignments for the employee, ordered chronologically by end date.
     * @return A list of {@link DepartmentManager} objects.
     */
    public List<DepartmentManager> getDeptManagerList() { return deptManagerList; }

    /**
     * Returns a string representation of the {@code Employee} object, including linked list information.
     *
     * @return A string containing key employee details and relationship lists.
     */
    @Override
    public String toString() {
        return "Employee{" +
                "emp_no=" + emp_no +
                ", birth_date=" + birth_date +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", hire_date=" + hire_date +
                ", titleList=" + titleList +
                ", salaryList=" + salaryList +
                ", deptEmpList=" + deptEmpList +
                ", deptManagerList=" + deptManagerList +
                '}';
    }
}