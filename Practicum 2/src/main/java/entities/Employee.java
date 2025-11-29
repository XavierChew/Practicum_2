package entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "emp_no")
    private int emp_no;

    @Column(name = "birth_date")
    private LocalDate birth_date;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "hire_date")
    private LocalDate hire_date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @JsonManagedReference
    private List<Titles> titleList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @JsonManagedReference
    private List<Salaries> salaryList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @JsonManagedReference
    private List<DepartmentEmployee> deptEmpList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @JsonManagedReference
    private List<DepartmentManager> deptManagerList;

    public Employee() {}

    // getters/setters — keep names consistent with service
    public int getEmp_no() { return emp_no; }
    public void setEmp_no(int emp_no) { this.emp_no = emp_no; }

    public LocalDate getBirth_date() { return birth_date; }
    public void setBirth_date(LocalDate birth_date) { this.birth_date = birth_date; }

    public String getFirst_name() { return first_name; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }

    public String getLast_name() { return last_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getHire_date() { return hire_date; }
    public void setHire_date(LocalDate hire_date) { this.hire_date = hire_date; }

    public List<Titles> getTitleList() { return titleList; }
    public List<Salaries> getSalaryList() { return salaryList; }
    public List<DepartmentEmployee> getDeptEmpList() { return deptEmpList; }
    public List<DepartmentManager> getDeptManagerList() { return deptManagerList; }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_no=" + emp_no +
                ", birth_date=" + birth_date +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", hire_date=" + hire_date +
                ", titleList=" + titleList +
                ", salaryList=" + salaryList +
                ", deptEmpList=" + deptEmpList +
                ", deptManagerList=" + deptManagerList +
                '}';
    }
}
