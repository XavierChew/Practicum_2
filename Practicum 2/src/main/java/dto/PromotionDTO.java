package dto;

/**
 * **PromotionDTO** is a **Data Transfer Object (DTO)** specifically designed to encapsulate
 * the necessary data for processing an employee promotion.
 *
 * <p>This DTO is used to package the target employee ID and all new attributes (salary, department, and title)
 * in a single object for transmission between the client and the server, typically as part of a RESTful request body.</p>
 *
 * * This DTO is used to facilitate the business operation of promoting an employee.
 * * It is **not persisted** to the database and does not directly map to a single entity.
 * * @author Ilyas & Wei Xian
 *
 */
public class PromotionDTO  {

    /**
     * The unique employee number of the employee being promoted.
     */
    public int empNo;

    /**
     * The **new** salary amount associated with the promotion.
     */
    public int salary;

    /**
     * The ID of the **new** department the employee is moving to, if applicable.
     */
    public String departmentID;

    /**
     * The **new** job title the employee will receive.
     */
    public String title;

<<<<<<< HEAD
    /** 
     * Default constructor for creating a new PromotionDTO object.
=======
    /**
     * Default public constructor.
>>>>>>> b577c8fd004072339d7e960818dbca705962e435
     */
    public PromotionDTO() {}

    /**
<<<<<<< HEAD
     * Constructor for creating a new PromotionDTO object.
     * @param employeeID The employee number.
     * @param salary The salary of the employee.
     * @param departmentID The department number.
     * @param title The title of the employee.
=======
     * Parameterized constructor for creating a {@code PromotionDTO} instance.
     *
     * @param employeeID The employee number.
     * @param salary The new salary amount.
     * @param departmentID The new department ID.
     * @param title The new title.
>>>>>>> b577c8fd004072339d7e960818dbca705962e435
     */
    public PromotionDTO(int employeeID, int salary, String departmentID, String title) {
        this.empNo = employeeID;
        this.salary = salary;
        this.departmentID = departmentID;
        this.title = title;
    }

    /**
<<<<<<< HEAD
     * Retrieves the title of the employee.
     * @return The title of the employee.
=======
     * Retrieves the new title.
     * @return The job title string.
>>>>>>> b577c8fd004072339d7e960818dbca705962e435
     */
    public String getTitle() {
        return title;
    }

    /**
<<<<<<< HEAD
     * Sets the title of the employee.
     * @param title The title of the employee to set.
=======
     * Sets the new title.
     * @param title The title string to set.
>>>>>>> b577c8fd004072339d7e960818dbca705962e435
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
<<<<<<< HEAD
     * Retrieves the department number of the employee.
     * @return The department number of the employee.
=======
     * Retrieves the new department ID.
     * @return The department ID string.
>>>>>>> b577c8fd004072339d7e960818dbca705962e435
     */
    public String getDepartmentID() {
        return departmentID;
    }

    /**
<<<<<<< HEAD
     * Sets the department number of the employee.
     * @param departmentID The department number of the employee to set.
=======
     * Sets the new department ID.
     * @param departmentID The department ID string to set.
>>>>>>> b577c8fd004072339d7e960818dbca705962e435
     */
    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    /**
<<<<<<< HEAD
     * Retrieves the salary of the employee.
     * @return The salary of the employee.
=======
     * Retrieves the new salary amount.
     * @return The salary amount.
>>>>>>> b577c8fd004072339d7e960818dbca705962e435
     */
    public int getSalary() {
        return salary;
    }

    /**
<<<<<<< HEAD
     * Sets the salary of the employee.
     * @param salary The salary of the employee to set.
=======
     * Sets the new salary amount.
     * @param salary The salary amount to set.
>>>>>>> b577c8fd004072339d7e960818dbca705962e435
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
<<<<<<< HEAD
     * Retrieves the employee number of the employee.
     * @return The employee number of the employee.
=======
     * Retrieves the employee number.
     * @return The employee number.
>>>>>>> b577c8fd004072339d7e960818dbca705962e435
     */
    public int getEmpNo() {
        return empNo;
    }

    /**
<<<<<<< HEAD
     * Sets the employee number of the employee.
     * @param empNo The employee number of the employee to set.
=======
     * Sets the employee number.
     * @param empNo The employee number to set.
>>>>>>> b577c8fd004072339d7e960818dbca705962e435
     */
    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    /**
<<<<<<< HEAD
     * Returns a string representation of the {@link PromotionDTO} object.
     * @return A string representation of the PromotionDTO object.
=======
     * Returns a string representation of the {@code PromotionDTO} object.
     *
     * @return A formatted string showing the promotion details.
>>>>>>> b577c8fd004072339d7e960818dbca705962e435
     */
    @Override
    public String toString() {
        return "promotion: " +empNo + "\t" + salary + "\t" + departmentID + "\t" + title;
    }
}