package ru.technoserv.dao;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeDao {

    int getID();

    void create(Employee employee);

    Employee read(int empID);

    void delete(int empID);

    List<Employee> getAllFromDept(int deptID);

    void deleteAllFromDept(int deptID);

    void updateDept(int empID, int newDeptID);

    void updatePosition(int empID, int newPosID);

    void updateGrade(int empID, int newGrdID);

    void updateSalary(int empID, BigDecimal newSalary);

}

