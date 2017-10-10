package ru.technoserv.exceptions;

public class CommonError {

    private int id;

    private String message;

    private String help = "http://docs.stuffengine.apiary.io/#introduction";

    public CommonError(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
