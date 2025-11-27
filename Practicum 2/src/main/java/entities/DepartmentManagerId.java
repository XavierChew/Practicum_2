package entities;


import java.io.Serializable;
import java.util.Objects;


public class DepartmentManagerId implements Serializable {
    private String dept_no;
    private int emp_no;

    public DepartmentManagerId() {}
    public DepartmentManagerId(String dept_no, int emp_no) {
        this.dept_no = dept_no;
        this.emp_no = emp_no;
    }

    //Getter and Setter
    public String getDept_no() {  return dept_no; }
    public void setDept_no(String dept_no) { this.dept_no = dept_no; }
    public int getEmp_no() {  return emp_no; }
    public void setEmp_no(int emp_no) { this.emp_no = emp_no; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentManagerId that = (DepartmentManagerId) o;
        // Compare all key fields
        return emp_no == that.emp_no && Objects.equals(dept_no, that.dept_no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dept_no, emp_no);
    }
}
