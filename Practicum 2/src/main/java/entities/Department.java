package entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "dept_no")
    private String deptNo; // Corrected to camelCase

    @Column(name = "dept_name")
    private String deptName; // Corrected to camelCase


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private List<DepartmentEmployee> departmentEmployees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private List<DepartmentManager> departmentManagers;

    public Department (){}


    public String getDeptNo() { return deptNo; }
    public void setDeptNo(String deptNo) { this.deptNo = deptNo; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }

    public List<DepartmentEmployee> getDepartmentEmployees() { return departmentEmployees; }
    public void setDepartmentEmployees(List<DepartmentEmployee> departmentEmployees) { this.departmentEmployees = departmentEmployees; }

    public List<DepartmentManager> getDepartmentManagers() { return departmentManagers; }
    public void setDepartmentManagers(List<DepartmentManager> departmentManagers) { this.departmentManagers = departmentManagers; }

    @Override
    public String toString() {
        return "Department [deptNo=" + deptNo + ", deptName=" + deptName + "]";
    }
}