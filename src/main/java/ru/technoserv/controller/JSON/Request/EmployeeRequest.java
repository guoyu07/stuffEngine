package ru.technoserv.controller.JSON.Request;

import ru.technoserv.dao.Employee;

public class EmployeeRequest extends Employee{

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
