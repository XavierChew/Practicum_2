package entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "dept_emp")
public class DepartmentEmployee {
    @Id private int emp_no;
    @Id private String dept_no;
    private Date from_date;
    private Date to_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_no")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_no")
    private Department department;



    public DepartmentEmployee() {
    }

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

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    @Override
    public String toString() {
        return "DepartmentEmployee{" +
                "emp_no=" + emp_no +
                ", dept_no='" + dept_no + '\'' +
                ", from_date=" + from_date +
                ", to_date=" + to_date +
                '}';
    }
}
