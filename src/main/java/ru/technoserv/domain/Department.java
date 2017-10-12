package ru.technoserv.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="DEPARTMENT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "department", propOrder = {
        "id",
        "parentDeptId",
        "deptName",
        "deptHeadId"
})
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

    @Size(min=5, max = 100, message = "Имя отдела должно содердать от 5 до 100 букв")
    @Column(name="DEPT_NAME")
    @XmlElement(required = true)
    private String deptName;

    @Column(name="DEPT_HEAD_ID")
    private Integer deptHeadId;

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
        return id+" "+deptName;
    }
}
