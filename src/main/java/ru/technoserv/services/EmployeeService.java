package ru.technoserv.services;

import ru.technoserv.dao.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    void getEmployeeStory(Employee employee);

    void transferEmployee(int empID, int depID);

    void removeEmployee(int id);

    void changeEmployeeSalary(Employee employee);

    void changeEmployeeGrade(Employee employee);

    void changeEmployeePosition(Employee employee);

    List<Employee> getEmployees(int depId);

    Employee getEmployee(int id);

}
