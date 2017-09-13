package ru.technoserv.exceptions;

public class CommonException extends RuntimeException {
    private final static int ERROR_ID = 1;
    private final static String SHORT_MESSAGE = "Неправильный запрос. Смотрите api проекта.";

    public int getErrorId() {
        return ERROR_ID;
    }

    public String getShortMessage() {
        return SHORT_MESSAGE;
    }
}
