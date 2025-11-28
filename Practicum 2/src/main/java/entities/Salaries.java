package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "salaries")
public class Salaries {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no")
    private Employee employee;

    private int salary;

    @Id private LocalDate from_date;

    private LocalDate to_date;


    public Employee getEmp_no() { return employee; }
    public void setEmp_no(Employee employee) { this.employee = employee; }
    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
    public LocalDate getFrom_date() { return from_date; }
    public void setFrom_date(LocalDate from_date) { this.from_date = from_date; }
    public LocalDate getTo_date() { return to_date; }
    public void setTo_date(LocalDate to_date) { this.to_date = to_date; }

    @Override
    public String toString() {
        return "Salary [emp_no=" + (employee != null ? employee.getEmp_no() : "N/A")
                + ", salary=" + salary + ", from_date=" + from_date
                + ", to_date=" + to_date + "]";
    }
}
