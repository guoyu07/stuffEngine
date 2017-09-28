package ru.technoserv.domain;


import java.math.BigDecimal;
import java.sql.Date;

public class Employee {

    private Integer empID;
    private Position position;
    private Grade grade;
    private Department department;
    private String lastName;
    private String firstName;
    private String patrName;
    private Character gender;
    private Date birthday;
    private BigDecimal salary;

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

    public void setEmpID(int empID) {
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

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
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

