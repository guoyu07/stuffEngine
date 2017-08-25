package ru.technoserv.repository;

import ru.technoserv.controller.Employee;

public interface EmployeeRepository {

    Employee getEmployeeById(int id);

}
