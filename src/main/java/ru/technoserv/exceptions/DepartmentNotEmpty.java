package ru.technoserv.exceptions;

public class DepartmentNotEmpty extends DepartmentException{
    private final static int ERROR_ID = 6;
    private final static String SHORT_MESSAGE = "Нельзя удалить отдел с сотрудниками";

    public DepartmentNotEmpty(int deptId){
        super(deptId);
    }



}
