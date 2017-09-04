package ru.technoserv.dao;

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

    public Department(Integer id, Integer parentDeptId, String deptName, Integer deptHeadId) {
        this.id = id;
        this.parentDeptId = parentDeptId;
        this.deptName = deptName;
        this.deptHeadId = deptHeadId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(Integer parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

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
