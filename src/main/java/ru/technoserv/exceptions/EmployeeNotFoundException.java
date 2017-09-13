package ru.technoserv.exceptions;

public class EmployeeNotFoundException extends EmployeeException {

    private final static int ERROR_ID = 4;
    private final static String SHORT_MESSAGE = "Сотрудник не найден";

    public EmployeeNotFoundException(int employeeId){
        super(employeeId);
    }

    public int getErrorId() {
        return ERROR_ID;
    }

    public String getShortMessage() {
        return SHORT_MESSAGE;
    }
}
