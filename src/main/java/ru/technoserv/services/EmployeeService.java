package ru.technoserv.services;

import ru.technoserv.dao.Employee;

import java.util.List;

public interface EmployeeService {

   void createEmployee(Employee employee);

    void getEmployeeStory(Employee employee);

    void transferEmployee(Employee employee);

    void removeEmployee(int id);

    void changeEmployeeSalary(Employee employee);

    void changeEmployeeGrade(Employee employee);

    void changeEmployeePosition(Employee employee);

    List<Employee> getEmployees(int depId);

    Employee getEmployee(int id);

}
