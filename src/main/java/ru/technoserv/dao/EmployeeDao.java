package ru.technoserv.dao;

public interface EmployeeDao {
    void create(Employee employee);
    Employee read(String firstName, String lastName);
    void delete(String firstName, String lastName);
}

