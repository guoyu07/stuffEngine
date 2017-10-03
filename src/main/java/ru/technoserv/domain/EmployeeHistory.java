package ru.technoserv.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Comparator;


@Entity
@Table(name = "EMPLOYEE2")
public class EmployeeHistory {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "emp2_gen"
    )
    @GenericGenerator(
            name = "emp2_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "EMPLOYEE_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Integer id;

    @Column(name = "CHRON_ID")
    private Integer empID;

    @Column(name = "START_DATE")
    private Timestamp startDate;

    @Column(name = "END_DATE")
    private Timestamp endDate;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @OneToOne
    @JoinColumn(name = "POSITION_ID")
    private Position position;

    @OneToOne
    @JoinColumn(name = "GRADE_ID")
    private Grade grade;

    @NotNull(message = "Необходимо указать отдел в котором работает сотрудник")
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @Size(min = 3, max = 50, message = "Фамилия должно быть от 3 до 50 символов")
    @Column(name = "LAST_NAME")
    private String lastName;

    @Size(min = 3, max = 50, message = "Имя должно быть от 3 до 50 символов")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "PATR_NAME")
    private String patrName;

    @NotNull(message = "Укажите ваш пол")
    @Column(name = "GENDER")
    private String gender;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @NotNull(message = "Укажите зарплату")
    @Min(value = 0, message = "Работники не платят за работу")
    @Column(name = "SALARY")
    private BigDecimal salary;

    public EmployeeHistory() {
    }

    public EmployeeHistory(Employee employee) {
        this.empID = employee.getEmpID();
        this.position = employee.getPosition();
        this.grade = employee.getGrade();
        this.lastName = employee.getLastName();
        this.firstName = employee.getFirstName();
        this.patrName = employee.getPatrName();
        this.gender = employee.getGender();
        this.birthday = employee.getBirthday();
        this.salary = employee.getSalary();
        this.department = employee.getDepartment();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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
        return "EmployeeHistory{" +
                "id=" + id +
                ", empID=" + empID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", position=" + position +
                ", grade=" + grade +
                ", department=" + department +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patrName='" + patrName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", salary=" + salary +
                '}';
    }

    public static Comparator<EmployeeHistory> HistoryComparator = new Comparator<EmployeeHistory>() {
        @Override
        public int compare(EmployeeHistory o1, EmployeeHistory o2) {
            return o2.getId().compareTo(o1.getId());
        }
    };
}
