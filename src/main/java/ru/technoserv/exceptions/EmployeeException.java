package ru.technoserv.exceptions;

public class EmployeeException extends CommonException {

    private final static int ERROR_ID = 2;
    private final static String SHORT_MESSAGE = "Неправильный формат сотрудника";
    private int employeeId;

    public EmployeeException(int employeeId){
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getErrorId() {
        return ERROR_ID;
    }

    public String getShortMessage() {
        return SHORT_MESSAGE;
    }
}
