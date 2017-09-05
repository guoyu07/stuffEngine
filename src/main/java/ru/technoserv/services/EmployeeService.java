package ru.technoserv.services;

import ru.technoserv.dao.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    void getEmployeeStory(Employee employee);

    Employee transferEmployee(int empID, int depID);

    void removeEmployee(int id);

    Employee changeEmployeeSalary(int empID, BigDecimal salary);

    Employee changeEmployeeGrade(int empID, int gradeID);

    Employee changeEmployeePosition(int empID, int positionID);

    List<Employee> getEmployees(int depId);

    Employee getEmployee(int id);

}
