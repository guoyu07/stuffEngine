package ru.technoserv.exceptions;

public class DepartmentHasSubDeptsException extends DepartmentException {
    private final static int ERROR_ID = 7;
    private final static String SHORT_MESSAGE = "Недопустимая операция. Нельзя удалить отдел с дочерними отделами";

    public DepartmentHasSubDeptsException(int departmentId) {
        super(departmentId);
    }

    public int getErrorId() {
        return ERROR_ID;
    }

    public String getShortMessage() {
        return SHORT_MESSAGE;
    }
}
