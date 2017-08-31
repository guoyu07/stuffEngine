package ru.technoserv.dao;

public class Department {
    private Long id;
    private Long parentDeptId;
    private String deptName;
    private Long deptHeadId;

    public Department() {
    }

    public Department(Long id, Long parentDeptId, String deptName, Long deptHeadId) {
        this.id = id;
        this.parentDeptId = parentDeptId;
        this.deptName = deptName;
        this.deptHeadId = deptHeadId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(Long parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getDeptHeadId() {
        return deptHeadId;
    }

    public void setDeptHeadId(Long deptHeadId) {
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
