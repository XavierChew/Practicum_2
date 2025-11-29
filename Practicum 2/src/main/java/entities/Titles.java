package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "titles")
@IdClass(TitleId.class)
public class Titles {
    @Id
    @Column(name= "emp_no")
    private int empNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "emp_no")
    @JsonBackReference
    private Employee employee;

    @Id
    private String title;

    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    public Titles(){}

    // getters and setters
    public int getEmpNo() { return empNo; }
    public void setEmpNo(int empNo) { this.empNo = empNo; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }

    @Override
    public String toString() {
        return "Titles{" +
                "empNo=" + empNo +
                ", title='" + title + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
