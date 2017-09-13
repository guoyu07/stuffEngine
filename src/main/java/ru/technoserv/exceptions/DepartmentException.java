package ru.technoserv.exceptions;

public class DepartmentException extends CommonException {

    private final static int ERROR_ID = 3;
    private final static String SHORT_MESSAGE = "Неправильный формат отдела";
    private int departmentId;

    public DepartmentException(int departmentId){
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getErrorId() {
        return ERROR_ID;
    }

    public String getShortMessage() {
        return SHORT_MESSAGE;
    }
}
