package ru.technoserv.dao;


import org.springframework.cache.annotation.Cacheable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;


public class Employee {

    private Integer empID;
    private String position;
    private String grade;
    @NotNull
    private String department;
    @NotNull
    @Size(min=3, max=50)
    private String lastName;
    @NotNull
    @Size(min=3, max=50)
    private String firstName;
    private String patrName;
    @NotNull
    private Character gender;
    private Date birthday;
    @NotNull
    private BigDecimal salary;
    private static Integer globalID;

    public static Integer getGlobalID() {
        return ++globalID;
    }

    public static void setGlobalID(Integer globalID) {
        Employee.globalID = globalID;
    }

    public Employee() {
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
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

