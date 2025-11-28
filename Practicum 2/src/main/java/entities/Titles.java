package entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "titles")
public class Titles {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "emp_no") private Employee employee;
    @Id private String title;
    @Id private LocalDate from_date;
    private LocalDate to_date;

    public Titles(){}

    public Employee getEmp_no() {
        return employee;
    }

    public void setEmp_no(Employee employee) {
        this.employee = employee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDate from_date) {
        this.from_date = from_date;
    }

    public LocalDate getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDate to_date) {
        this.to_date = to_date;
    }

    @Override
    public String toString() {
        return "Titles{" +
                "employee=" + employee +
                ", title='" + title + '\'' +
                ", from_date=" + from_date +
                ", to_date=" + to_date +
                '}';
    }
}
