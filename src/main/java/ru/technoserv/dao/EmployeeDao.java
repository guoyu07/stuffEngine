package ru.technoserv.dao;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeDao {
    void create(Employee employee);

    Employee read(int empID);

    void delete(int empID);

    List<Employee> getAllFromDept(String deptName);

    void deleteAllFromDept(String deptName);

    void updateDept(int empID, String newDept);

    void updatePosition(int empID, String newPosition);

    void updateGrade(int empID, String newGrade);

    void updateSalary(int empID, BigDecimal newSalary);
}

