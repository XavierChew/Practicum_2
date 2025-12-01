package dto;

public class PromotionDTO  {
    public int empNo;
    public int salary;
    public String departmentID;
    public String title;

    public PromotionDTO() {}

    public PromotionDTO(int employeeID, int salary, String departmentID, String title) {
        this.empNo = employeeID;
        this.salary = salary;
        this.departmentID = departmentID;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }



}
