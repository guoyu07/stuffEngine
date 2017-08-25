package ru.technoserv.repository;

import ru.technoserv.dao.Employee;

public interface EmployeeRepository {

    Employee getEmployeeById(int id);

}
