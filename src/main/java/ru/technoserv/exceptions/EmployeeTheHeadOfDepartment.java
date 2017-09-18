package ru.technoserv.exceptions;

public class EmployeeTheHeadOfDepartment extends EmployeeException{

    private final static int ERROR_ID = 5;
    private final static String SHORT_MESSAGE = "Недопустимая операция. Сотрудник является начальником отдела";

    public EmployeeTheHeadOfDepartment(int employeeId){
        super(employeeId);
    }

    public int getErrorId() {
        return ERROR_ID;
    }

    public String getShortMessage() {
        return SHORT_MESSAGE;
    }
}
