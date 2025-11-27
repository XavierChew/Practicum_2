package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
    @Id @Column(name = "dept_no") private String dept_no;
    private String dept_name;

    public Department (){}

    // getter setter
    public String getDept_no() { return dept_no; }
    public void setDept_no(String dept_no) { this.dept_no = dept_no; }
    public String getDept_name() { return dept_name; }
    public void setDept_name(String dept_name) { this.dept_name = dept_name; }


    @Override
    public String toString() {
        return "Employee [dept_no=" + dept_no + ", dept_name=" + dept_name + "]";
    }
}
