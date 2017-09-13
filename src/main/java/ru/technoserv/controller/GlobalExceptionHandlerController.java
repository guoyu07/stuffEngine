package ru.technoserv.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.technoserv.exceptions.CommonError;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationProblem(MethodArgumentNotValidException e){
        List<ObjectError> errors= e.getBindingResult().getAllErrors();
        List<CommonError> myErrors = new ArrayList<>();
        for (ObjectError err: errors
                ) {
            myErrors.add(new CommonError(7, err.getDefaultMessage()));
        }
        String json = GsonUtility.toJson(myErrors);
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

}
