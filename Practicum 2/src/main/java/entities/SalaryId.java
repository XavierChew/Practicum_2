package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SalaryId implements Serializable {

    private int empNo;        // use primitive PK field, not Employee
    private LocalDate fromDate;



    public SalaryId() {}

    public SalaryId(int empNo, LocalDate fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }

    // getters & setters
    public int getEmpNo() { return empNo; }
    public void setEmpNo(int empNo) { this.empNo = empNo; }

    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryId salaryId = (SalaryId) o;
        return empNo == salaryId.empNo && Objects.equals(fromDate, salaryId.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, fromDate);
    }
}
