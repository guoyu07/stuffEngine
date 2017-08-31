package ru.technoserv.services;

import ru.technoserv.dao.Employee;

public interface EmployeeService {

    void createEmployee(Employee employee);

    void getEmployeeStory(Employee employee);

    void transferEmployee(Employee employee);

    void removeEmployee(Employee employee);

    void changeEmployeeData(Employee employee);

}
