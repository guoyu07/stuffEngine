package ru.technoserv.exceptions;

public class CommonError {

    private int code;

    private String message;

    public CommonError(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
