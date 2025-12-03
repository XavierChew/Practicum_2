package dto;

/**
 * PromotionDTO is a data transfer object for the Promotion entity.
 * It is used to transfer data between the client and the server.
 * It is not an entity itself, but it is used to transfer data between the client and the server.
 * It is not persisted to the database.
 * @author: Ilyas & Wei Xian
 */
public class PromotionDTO  {
    public int empNo;
    public int salary;
    public String departmentID;
    public String title;

    /** 
     * Default constructor for creating a new PromotionDTO object.
     */
    public PromotionDTO() {}

    /**
     * Constructor for creating a new PromotionDTO object.
     * @param employeeID The employee number.
     * @param salary The salary of the employee.
     * @param departmentID The department number.
     * @param title The title of the employee.
     */
    public PromotionDTO(int employeeID, int salary, String departmentID, String title) {
        this.empNo = employeeID;
        this.salary = salary;
        this.departmentID = departmentID;
        this.title = title;
    }

    /**
     * Retrieves the title of the employee.
     * @return The title of the employee.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the employee.
     * @param title The title of the employee to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the department number of the employee.
     * @return The department number of the employee.
     */
    public String getDepartmentID() {
        return departmentID;
    }

    /**
     * Sets the department number of the employee.
     * @param departmentID The department number of the employee to set.
     */
    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * Retrieves the salary of the employee.
     * @return The salary of the employee.
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Sets the salary of the employee.
     * @param salary The salary of the employee to set.
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Retrieves the employee number of the employee.
     * @return The employee number of the employee.
     */
    public int getEmpNo() {
        return empNo;
    }

    /**
     * Sets the employee number of the employee.
     * @param empNo The employee number of the employee to set.
     */
    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    /**
     * Returns a string representation of the {@link PromotionDTO} object.
     * @return A string representation of the PromotionDTO object.
     */
    @Override
    public String toString() {
        return "promotion: " +empNo + "\t" + salary + "\t" + departmentID + "\t" + title;
    }


}
