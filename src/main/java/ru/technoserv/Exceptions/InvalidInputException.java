package ru.technoserv.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidInputException extends RuntimeException {

    private String message = "";

    public InvalidInputException(String message){
        this.message = message;
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error invalidInput(InvalidInputException e){
        return new Error(message);
    }
}
