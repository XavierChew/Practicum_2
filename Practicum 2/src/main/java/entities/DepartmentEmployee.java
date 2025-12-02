package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * DepartmentEmployee is an entity that represents a department employee in the database.
 * It is used to store information about a department employee.
 * It is persisted to the database.
 * @author: Ilyas & Wei Xian
 */
@Entity
@IdClass(DepartmentEmployeeId.class)
@Table(name = "dept_emp")
@NamedQuery(name = "DepartmentEmployee.updateDepartmentDate", 
query = "UPDATE DepartmentEmployee de SET de.toDate = :toDate WHERE de.empNo = :emp_no AND de.toDate = :date")
public class DepartmentEmployee {

    @Id
    @Column(name = "emp_no")
    private int empNo;

    @Id
    @Column(name = "dept_no")
    private String deptNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    @JsonBackReference // avoids recursion
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Department department;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;


    public DepartmentEmployee() {
    }

    public DepartmentEmployee(int empNo, String deptNo, LocalDate fromDate) {
        this.empNo = empNo;
        this.deptNo = deptNo;
        this.fromDate = fromDate;
        this.toDate = LocalDate.of(9999, 1, 1);
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
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