package dto;

import java.time.LocalDate;

public class EmployeeDTO {
    private int emp_no;
    private String first_name;
    private String last_name;
    private LocalDate hire_date;

    public EmployeeDTO(int emp_no, String first_name, String last_name, LocalDate hire_date) {
        this.emp_no = emp_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hire_date = hire_date;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public String getFirst_name() {
        return first_name;
    }
    
    public String getLast_name() {
        return last_name;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }
}
