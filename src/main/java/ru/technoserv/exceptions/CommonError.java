package ru.technoserv.exceptions;

public class CommonError {

    private int id;

    private String message;

    private static final String help = "http://docs.stuffengine.apiary.io/#introduction";

    public CommonError(StuffExceptions exception){
        message = exception.getMessage();
        id = exception.getErrorId();
    }

    public CommonError(String validationError){
        message = validationError;
        id = 7;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public static String getHelpMessage() {
        return help;
    }
}
