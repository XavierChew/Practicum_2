package entities;

import java.io.Serializable;
import java.util.Objects;

public class DepartmentEmployeeId implements Serializable {

    private int empNo;
    private String deptNo;

    public DepartmentEmployeeId() {}

    public DepartmentEmployeeId(int empNo, String deptNo) {
        this.empNo = empNo;
        this.deptNo = deptNo;
    }

    public int getEmpNo() { return empNo; }
    public void setEmpNo(int empNo) { this.empNo = empNo; }
    public String getDeptNo() { return deptNo; }
    public void setDeptNo(String deptNo) { this.deptNo = deptNo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentEmployeeId)) return false;
        DepartmentEmployeeId that = (DepartmentEmployeeId) o;
        return empNo == that.empNo && Objects.equals(deptNo, that.deptNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, deptNo);
    }
}
