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

    /**
     * Default public constructor.
     */
    public PromotionDTO() {}

    /**
     * Parameterized constructor for creating a new PromotionDTO object.
     *
     * @param employeeID The employee number.
     * @param salary The **new** salary of the employee.
     * @param departmentID The **new** department ID.
     * @param title The **new** title of the employee.
     */
    public PromotionDTO(int employeeID, int salary, String departmentID, String title) {
        this.empNo = employeeID;
        this.salary = salary;
        this.departmentID = departmentID;
        this.title = title;
    }

    /**
     * Retrieves the new title of the employee.
     * @return The new title of the employee.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the new title of the employee.
     * @param title The new title of the employee to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the new department ID.
     * @return The new department ID string.
     */
    public String getDepartmentID() {
        return departmentID;
    }

    /**
     * Sets the new department ID.
     * @param departmentID The new department ID string to set.
     */
    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * Retrieves the new salary amount.
     * @return The new salary amount.
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Sets the new salary amount.
     * @param salary The new salary amount to set.
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Retrieves the employee number.
     * @return The employee number.
     */
    public int getEmpNo() {
        return empNo;
    }

    /**
     * Sets the employee number.
     * @param empNo The employee number to set.
     */
    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    /**
     * Returns a string representation of the {@code PromotionDTO} object.
     *
     * @return A formatted string showing the promotion details.
     */
    @Override
    public String toString() {
        return "promotion: " +empNo + "\t" + salary + "\t" + departmentID + "\t" + title;
    }
}