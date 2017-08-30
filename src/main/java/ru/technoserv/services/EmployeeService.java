package ru.technoserv.services;

import ru.technoserv.controller.JSON.Request.EmployeeRequest;

public interface EmployeeService {

    void createEmployee(EmployeeRequest request);

    void getEmployeeStory(EmployeeRequest request);

    void transferEmployee(EmployeeRequest request);

    void removeEmployee(EmployeeRequest request);

    void changeEmployeeData(EmployeeRequest request);

}
