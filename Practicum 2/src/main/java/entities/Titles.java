package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "titles")
@IdClass(TitleId.class)
@NamedQueries({
        @NamedQuery(name = "Titles.updateTitleDate", query = "UPDATE Titles t SET t.toDate = :toDate WHERE t.empNo = :emp_no AND t.toDate = :date"),
        @NamedQuery(name = "Titles.findLatestTitle", query = "SELECT t from Titles t WHERE t.empNo = :emp_no AND t.toDate = :date")
})
public class Titles {
    @Id
    @Column(name= "emp_no")
    @JsonIgnore
    private int empNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "emp_no", insertable = false, updatable = false)
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

    public Titles(int empNo, String title, LocalDate fromDate) {
        this.empNo = empNo;
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = LocalDate.of(9999, 1, 1);
    }

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
