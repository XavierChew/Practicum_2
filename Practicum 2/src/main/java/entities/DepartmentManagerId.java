package entities;

import java.io.Serializable;
import java.util.Objects;

public class DepartmentManagerId implements Serializable {
    private String deptNo;
    private int empNo;

    public DepartmentManagerId() {}

    public DepartmentManagerId(String deptNo, int empNo) {
        this.deptNo = deptNo;
        this.empNo = empNo;
    }

    // getters and setters
    public String getDeptNo() { return deptNo; }
    public void setDeptNo(String deptNo) { this.deptNo = deptNo; }
    public int getEmpNo() { return empNo; }
    public void setEmpNo(int empNo) { this.empNo = empNo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentManagerId)) return false;
        DepartmentManagerId that = (DepartmentManagerId) o;
        return empNo == that.empNo && Objects.equals(deptNo, that.deptNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptNo, empNo);
    }
}
