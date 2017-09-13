package ru.technoserv.exceptions;

public class DepartmentNotFoundException extends DepartmentException {

    private final static int ERROR_ID = 4;
    private final static String SHORT_MESSAGE = "Отдел не найден";
    public DepartmentNotFoundException(int departmentId){
        super(departmentId);
    }

    public int getErrorId() {
        return ERROR_ID;
    }

    public String getShortMessage() {
        return SHORT_MESSAGE;
    }
}
