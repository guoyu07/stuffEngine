package ru.technoserv.services;

import ru.technoserv.domain.Employee;
import ru.technoserv.domain.EmployeeHistory;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<EmployeeHistory> getEmployeeStory(int id);

    void removeEmployee(int id);

    Employee changeEmployee(Employee employee);

    List<Employee> getEmployees(int depId);

    List<Employee> getAllEmployees();

    Employee getEmployee(int id);

    List<Employee> getPartOfEmployeeList(int start, int num);

}
