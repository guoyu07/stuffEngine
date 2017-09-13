package ru.technoserv.dao;

import ru.technoserv.domain.Employee;

import java.util.List;

public interface EmployeeDao {

    void create(Employee employee);

    Employee read(int empID);

    void delete(int empID);

    List<Employee> getAllFromDept(int deptID);

    Employee updateEmployee(Employee employee);
}

