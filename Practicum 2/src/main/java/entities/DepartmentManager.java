package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@IdClass(DepartmentManagerId.class)
@Table(name = "dept_manager")
public class DepartmentManager {

    @Id
    @Column(name = "dept_no")
    private String deptNo;

    @Id
    @Column(name = "emp_no")
    @JsonIgnore
    private int empNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no")
    @JsonBackReference
    private Employee employee;



    @Column(name = "from_date")
    private LocalDate fromDate; // Corrected to camelCase

    @Column(name = "to_date")
    private LocalDate toDate; // Corrected to camelCase


    public DepartmentManager() {}

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getFromDate() { // Corrected getter
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) { // Corrected setter
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() { // Corrected getter
        return toDate;
    }

    public void setToDate(LocalDate toDate) { // Corrected setter
        this.toDate = toDate;
    }
}