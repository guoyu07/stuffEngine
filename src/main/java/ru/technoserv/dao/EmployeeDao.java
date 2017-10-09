package ru.technoserv.dao;

import ru.technoserv.domain.Employee;
import ru.technoserv.domain.EmployeeHistory;

import java.util.List;

public interface EmployeeDao {

    Integer create(Employee employee);

    Employee read(int empID);

    void delete(int empID);

    List<Employee> getAllFromDept(int deptID);

    List<Employee> getAllEmployees();

    List<EmployeeHistory> getEmployeeStory(int empID);

    Employee updateEmployee(Employee employee);

}

