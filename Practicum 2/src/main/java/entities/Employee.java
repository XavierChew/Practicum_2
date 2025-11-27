package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {
    @Id @Column(name = "emp_no") private int emp_no;
    @Column(name = "birth_date") private LocalDate birth_date;
    @Column(name = "first_name") private String first_name;
    @Column(name = "last_name") private String last_name;
    @Column(name = "gender") private String gender;
    @Column(name = "hire_date") private LocalDate hire_date;
    
    public int getEmp_no() {
        return emp_no;
    }
    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }
    public LocalDate getBirth_date() {
        return birth_date;
    }
    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public LocalDate getHire_date() {
        return hire_date;
    }
    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    @Override
    public String toString() {
        return "Employee \nemp_no = " + emp_no + "\n birth_date = " + birth_date + "\n first_name = " + first_name + "\n last_name = " + last_name + "\n gender = " + gender + "\n hire_date = " + hire_date;
    }
}
