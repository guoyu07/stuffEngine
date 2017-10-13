package ru.technoserv.exceptions;

public class MyRuntimeException extends RuntimeException {

    private final StuffExceptions exception;

    public MyRuntimeException(StuffExceptions exception){
        this.exception = exception;
    }

    public StuffExceptions getException() {
        return exception;
    }
}
