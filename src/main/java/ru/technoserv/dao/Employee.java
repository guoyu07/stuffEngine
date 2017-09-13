package ru.technoserv.dao;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "emp_generator"
    )
    @GenericGenerator(
            name = "emp_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "EMPLOYEE_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "15"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name="EMP_ID")
    private Integer empID;

    @OneToOne
    @JoinColumn(name="POSITION_ID")
    private Position position;

    @OneToOne
    @JoinColumn(name="GRADE_ID")
    private Grade grade;
    @NotNull(message = "Необходимо указать отдел в котором работает сотрудник")
    @ManyToOne
    @JoinColumn(name="DEPARTMENT_ID")
    private Department department;
    @Size(min=3, max=50,message = "Фамилия должно быть от 3 до 50 символов")
    @Column(name="LAST_NAME")
    private String lastName;
    @Size(min=3, max=50,message = "Имя должно быть от 3 до 50 символов")
    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="PATR_NAME")
    private String patrName;
    @NotNull(message = "Укажите ваш пол")
    @Column(name="GENDER")
    private Character gender;

    @Column(name="BIRTHDAY")
    private Date birthday;
    @NotNull(message="Укажите зарплату")
    @Min(value=0,message = "Не все сотрудники работают за еду")
    @Column(name="SALARY")
    private BigDecimal salary;

    public Employee() {
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

