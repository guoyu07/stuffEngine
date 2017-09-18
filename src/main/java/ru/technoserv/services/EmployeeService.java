package ru.technoserv.services;

import ru.technoserv.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    void getEmployeeStory(Employee employee);

    void removeEmployee(int id);

    Employee changeEmployee(Employee employee);
    List<Employee> getEmployees(int depId);

    Employee getEmployee(int id);

}
