package ru.technoserv.dao;

import javax.persistence.*;

@Entity
@Table(name="DEPARTMENT")
public class Department {
    private Integer id;
    private Integer parentDeptId;
    private String deptName;
    private Integer deptHeadId;

    private static Integer globalID;

    public static Integer getGlobalID() {
        return ++globalID;
    }

    public static void setGlobalID(Integer globalID) {
        Department.globalID = globalID;
    }

    public Department() {
    }

    @Id
    @Column(name="DEPT_ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="PARENT_DEPT_ID")
    public Integer getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(Integer parentDeptId) {
        this.parentDeptId = parentDeptId;
    }
    @Column(name="DEPT_NAME")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Column(name="DEPT_HEAD_ID")
    public Integer getDeptHeadId() {
        return deptHeadId;
    }

    public void setDeptHeadId(Integer deptHeadId) {
        this.deptHeadId = deptHeadId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", parentDeptId=" + parentDeptId +
                ", deptName='" + deptName + '\'' +
                ", deptHeadId=" + deptHeadId +
                '}';
    }
}
