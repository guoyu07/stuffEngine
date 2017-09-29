package ru.technoserv.exceptions;

public class CommonError {


    private String message;

    public CommonError(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
