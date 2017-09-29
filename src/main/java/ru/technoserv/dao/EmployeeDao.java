package ru.technoserv.dao;

import ru.technoserv.domain.EmployeeHistory;

import java.util.List;

public interface EmployeeDao {

    Integer create(EmployeeHistory employee);

    EmployeeHistory read(int empID);

    void delete(int empID);

    List<EmployeeHistory> getAllFromDept(int deptID);

    List<EmployeeHistory> getAllEmployees();

    List<EmployeeHistory> getEmployeeStory(int empID);

    EmployeeHistory updateEmployee(EmployeeHistory employee);

}

