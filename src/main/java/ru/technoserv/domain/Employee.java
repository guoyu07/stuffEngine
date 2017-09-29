package ru.technoserv.domain;


import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employee", propOrder = {
        "empID",
        "position",
        "grade",
        "department",
        "lastName",
        "firstName",
        "patrName",
        "gender",
        "birthday",
        "salary"
})
public class Employee {

    protected Integer empID;
    @XmlElement(required = true)
    protected Position position;
    @XmlElement(required = true)
    protected Grade grade;
    @XmlElement(required = true)
    protected Department department;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String patrName;
    @XmlElement(required = true)
    protected String gender;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected Date birthday;
    @XmlElement(required = true)
    protected BigDecimal salary;

    public Employee() {
    }

    public Employee(EmployeeHistory eh) {
        this.empID = eh.getEmpID();
        this.position = eh.getPosition();
        this.grade = eh.getGrade();
        this.lastName = eh.getLastName();
        this.firstName = eh.getFirstName();
        this.patrName = eh.getPatrName();
        this.gender = eh.getGender();
        this.birthday = eh.getBirthday();
        this.salary = eh.getSalary();
        this.department = eh.getDepartment();
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatrName() {
        return patrName;
    }

    public void setPatrName(String patrName) {
        this.patrName = patrName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", position='" + position + '\'' +
                ", grade='" + grade + '\'' +
                ", department='" + department + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patrName='" + patrName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", salary=" + salary +
                '}';
    }
}

