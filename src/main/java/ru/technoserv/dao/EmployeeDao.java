package ru.technoserv.dao;

public interface EmployeeDao {
    void create(Employee employee);
    Employee read(int empID);
    void delete(int empID);
}

