package ru.technoserv.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
public class DepWithChildren {

    @Id
    @Column(name = "DEPT_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PARENT_DEPT_ID")
    private Department parentDept;

    @Size(min = 5, max = 100, message = "Имя отдела должно содердать от 5 до 100 букв")
    @Column(name = "DEPT_NAME")
    private String deptName;

    @Column(name = "DEPT_HEAD_ID")
    private Integer deptHeadId;

    @OneToMany(mappedBy = "parentDept", fetch = FetchType.EAGER)
    @OrderBy(value = "deptName")
    private Set<DepWithChildren> children = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getParentDept() {
        return parentDept;
    }

    public void setParentDept(Department parentDept) {
        this.parentDept = parentDept;
    }

    public Set<DepWithChildren> getChildren() {
        return children;
    }

    public void setChildren(Set<DepWithChildren> children) {
        this.children = children;
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
        return "DepWithChildren{" +
                "id=" + id +
                ", parentDept=" + parentDept +
                ", deptName='" + deptName + '\'' +
                ", deptHeadId=" + deptHeadId +
                ", children=" + children +
                '}';
    }
}
