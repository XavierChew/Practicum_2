package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "titles")
public class Titles {

    @Id private int emp_no;
    private String title;
    private Date from_date;
    private Date to_date;

    public Titles(){}

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    @Override
    public String toString() {
        return "Titles{" +
                "emp_no=" + emp_no +
                ", title='" + title + '\'' +
                ", from_date=" + from_date +
                ", to_date=" + to_date +
                '}';
    }
}
