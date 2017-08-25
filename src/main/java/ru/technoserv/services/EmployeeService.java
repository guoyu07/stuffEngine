package ru.technoserv.services;

import ru.technoserv.controller.Employee;

public interface EmployeeService {

    void addEmployee(String firstName, String lastName);
    Employee getEmployee(int id);

}
