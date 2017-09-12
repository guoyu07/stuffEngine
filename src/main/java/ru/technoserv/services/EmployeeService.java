package ru.technoserv.services;

import ru.technoserv.dao.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    void getEmployeeStory(Employee employee);

    void removeEmployee(int id);

    Employee changeEmployee(Employee employee);
    List<Employee> getEmployees(int depId);

    Employee getEmployee(int id);

}
