package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "salaries")
public class Salaries {
    @Id
    @Column(name = "emp_no") private int emp_no;
    private int salary;
    private LocalDate from_date;
    private LocalDate to_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_no")
    private Employee employee;

    public int getEmp_no() { return emp_no; }
    public void setEmp_no(int emp_no) { this.emp_no = emp_no; }
    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
    public LocalDate getFrom_date() { return from_date; }
    public void setFrom_date(LocalDate from_date) { this.from_date = from_date; }
    public LocalDate getTo_date() { return to_date; }
    public void setTo_date(LocalDate to_date) { this.to_date = to_date; }

    @Override
    public String toString() {
        return "Employee [emp_no=" + emp_no  + ", salary=" + salary + ", from_date=" + from_date
                + ", to_date=" + to_date + "]";
    }
}
