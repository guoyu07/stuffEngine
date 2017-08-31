package ru.technoserv.dao;

public class Department {
    private long id;
    private Department parentDept;
    private String deptName;
    private Employee deptHead;

    public Department() {
    }

    public Department(long id, Department parentDept, String deptName, Employee deptHead) {
        this.id = id;
        this.parentDept = parentDept;
        this.deptName = deptName;
        this.deptHead = deptHead;
    }

    public Department(long id, String deptName) {
        this(id, null, deptName, null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Department getParentDept() {
        return parentDept;
    }

    public void setParentDept(Department parentDept) {
        this.parentDept = parentDept;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Employee getDeptHead() {
        return deptHead;
    }

    public void setDeptHead(Employee deptHead) {
        this.deptHead = deptHead;
    }
}
