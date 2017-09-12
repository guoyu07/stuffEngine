package ru.technoserv.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="DEPARTMENT")
public class Department {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dept_generator"
    )
    @GenericGenerator(
            name = "dept_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "DEPARTMENT_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "21"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name="DEPT_ID")
    private Integer id;

    @Column(name="PARENT_DEPT_ID")
    private Integer parentDeptId;

    @Column(name="DEPT_NAME")
    private String deptName;

    @Column(name="DEPT_HEAD_ID")
    private Integer deptHeadId;

//    private static Integer globalID;
//
//    public static Integer getGlobalID() {
//        return ++globalID;
//    }
//
//    public static void setGlobalID(Integer globalID) {
//        Department.globalID = globalID;
//    }

    public Department() {
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
