package ru.technoserv.services;

import ru.technoserv.dao.Employee;

public interface EmployeeService {

    void addEmployee(String firstName, String lastName);
    Employee getEmployee(String firstName, String lastName);

}
