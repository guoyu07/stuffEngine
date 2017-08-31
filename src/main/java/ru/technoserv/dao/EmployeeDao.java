package ru.technoserv.dao;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeDao {
    void create(Employee employee);

    Employee read(int empID);

    void delete(int empID);

    List<Employee> getAllFromDept(int deptID)

    void deleteAllFromDept(int deptID);

    void updateDept(int empID, int deptID);

    void updatePsoition(int empID, String newPosition);

    void updateGrade(int empID, String newGrade);

    void updateSalary(int empID, BigDecimal newSalary);

}

