package ru.technoserv.dao;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeDao {

//    int getID();

    void create(Employee employee);

    Employee read(int empID);

    void delete(int empID);

    List<Employee> getAllFromDept(int deptID);

    Employee updateEmployee(Employee employee);
}

