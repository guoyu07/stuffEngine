package ru.technoserv.dao;

import java.math.BigDecimal;

public interface EmployeeDao {
    void create(Employee employee);

    Employee read(String firstName, String lastName);

    void delete(String firstName, String lastName);

    void deleteAllFromDept(Department dept);

    void updateDept(Employee emp, Department dept);

    void updatePsoition(Employee emp, String newPosition);

    void updateGrade(Employee emp, String newGrade);

    void updateSalary(Employee emp, BigDecimal newSalary);

}

