package entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@IdClass(DepartmentManagerId.class)
@Table(name = "dept_manager")
public class DepartmentManager {
    @Id @Column(name="dept_no") private String dept_no;

    @Id @Column(name="emp_no") private int emp_no;

    private Date from_date;
    private Date to_date;


}
