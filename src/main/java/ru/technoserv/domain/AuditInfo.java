package ru.technoserv.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="AUDIT_INFO")
public class AuditInfo {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "audit_generator"
    )
    @GenericGenerator(
            name = "audit_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "AUDIT_INFO_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name="ID")
    private Integer id;
    @Column(name="DEPARTMENT_ID")
    private Integer deptId;
    @Column(name="EMPLOYEE_ID")
    private Integer empId;
    @Column(name=("IP_ADDR"))
    private String ipAddr;
    @Column(name="REQUEST_OBJECT")
    private String requestObject;
    @OneToOne
    @JoinColumn(name = "ACTIONN")
    private Action action;
    @Column(name="REQUEST_DATE")
    private Timestamp requestDate;
    @Column(name="EXCEPTION_MESSAGE")
    private String exceptionMessage;



    public AuditInfo(Integer deptId, Integer empId, String ipAddr, String requestObject, int actionId) {
        this.deptId = deptId;
        this.empId = empId;
        this.ipAddr = ipAddr;
        this.requestObject = requestObject;
        this.action = new Action(actionId);
        this.requestDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public AuditInfo(Integer deptId, Integer empId, String ipAddr, String requestObject, int actionId, String exceptionMessage) {
        this.deptId = deptId;
        this.empId = empId;
        this.ipAddr = ipAddr;
        this.requestObject = requestObject;
        this.action = new Action(actionId);
        this.requestDate = Timestamp.valueOf(LocalDateTime.now());
        this.exceptionMessage = exceptionMessage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(String requestObject) {
        this.requestObject = requestObject;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        return "AuditInfo{" +
                "id=" + id +
                ", deptId=" + deptId +
                ", empId=" + empId +
                ", ipAddr='" + ipAddr + '\'' +
                ", requestObject='" + requestObject + '\'' +
                ", action=" + action +
                ", requestDate=" + requestDate +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                '}';
    }
}
