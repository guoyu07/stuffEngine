package ru.technoserv.exceptions;

public enum StuffExceptions {
    //NOTE: number 7 for validation
    SERVER_ERROR(0, "Произошла непредвиденная ошибка сервера"),
    DATABASE_ERROR(1, "Ошибка при обращении к базе данных. Неправильный запрос или база данных не доступна."),
    EMPLOYEE_THE_HEAD_ERROR(2, "Не допустимое действие с начальником отдела. Возможно стоит сначала сменить начальника отдела."),
    EMPLOYEES_IN_DEPARTMENT(3, "В отделе есть сотрудники, действие недопустимо"),
    SUBDEPTS_ERROR(4, "У отдела есть подотделы, действие недопустимо"),
    NOT_FOUND(5, "Запрашиваемый объект не найден");

    private int code;

    private String message;

    private StuffExceptions(int code, String message){

        this.code = code;
        this.message = message;

    }

    public int getErrorId() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return code+":"+message;
    }
}
