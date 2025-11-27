package entities;


import java.util.Objects;

public class DepartmentEmployeeId {
    private int emp_no;
    private String dept_no;

    public DepartmentEmployeeId() {}
    public DepartmentEmployeeId(int emp_no, String dept_no) {
        this.emp_no = emp_no;
    }

    // Getter and Setter
    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEmployeeId that = (DepartmentEmployeeId) o;
        return emp_no == that.emp_no && Objects.equals(dept_no, that.dept_no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emp_no, dept_no);
    }
}
