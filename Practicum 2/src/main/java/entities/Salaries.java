package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@IdClass(SalaryId.class)
@Table(name = "salaries")
@NamedQuery(name = "Salaries.updateSalaryDate", 
query = "UPDATE Salaries s SET s.toDate = :toDate WHERE s.empNo = :emp_no AND s.toDate = :date")
public class Salaries {

    @Id
    @Column(name = "emp_no")
    @JsonIgnore
    private int empNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    @JsonBackReference
    private Employee employee;


    private int salary;

    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    public Salaries() {};

    public Salaries(int empNo, int salary, LocalDate fromDate) {
        this.empNo = empNo;
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = LocalDate.of(9999, 1, 1);
    }


    public int getEmpNo() {return empNo;}
    public void setEmpNo(int empNo) {this.empNo = empNo;}
    public Employee getEmployee() { return employee; } // Corrected getter name (was getEmp_no)
    public void setEmployee(Employee employee) { this.employee = employee; } // Corrected setter name (was setEmp_no)
    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
    public LocalDate getFromDate() { return fromDate; } // Corrected getter name
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; } // Corrected setter name
    public LocalDate getToDate() { return toDate; } // Corrected getter name
    public void setToDate(LocalDate toDate) { this.toDate = toDate; } // Corrected setter name

    @Override
    public String toString() {
        return "Salaries{" +
                "empNo=" + empNo +
                ", employee=" + employee +
                ", salary=" + salary +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}