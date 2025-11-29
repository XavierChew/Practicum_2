package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@IdClass(DepartmentEmployeeId.class)
@Table(name = "dept_emp")
public class DepartmentEmployee {

    @Id
    @Column(name = "emp_no")
    private int empNo;

    @Id
    @Column(name = "dept_no")
    private String deptNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Department department;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;


    public DepartmentEmployee() {
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getFromDate() { // Corrected getter name
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) { // Corrected setter name
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() { // Corrected getter name
        return toDate;
    }

    public void setToDate(LocalDate toDate) { // Corrected setter name
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        // Updated toString() to use camelCase getters from Department and local fields
        return "DepartmentEmployee{" +
                "emp_no=" + employee.getEmp_no() +
                ", dept_no='" + department.getDeptNo() + "'" + '\'' +
                ", from_date=" + fromDate +
                ", to_date=" + toDate +
                '}';
    }
}