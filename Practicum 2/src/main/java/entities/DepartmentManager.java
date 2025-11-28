package entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@IdClass(DepartmentManagerId.class)
@Table(name = "dept_manager")
public class DepartmentManager {
    @Id @Column(name="dept_no") private String dept_no;

    @Id @Column(name="emp_no") private int emp_no;

    @ManyToOne
    @JoinColumn(name = "dept_no")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_no")
    private Employee employee;

    private LocalDate from_date;
    private LocalDate to_date;


}
