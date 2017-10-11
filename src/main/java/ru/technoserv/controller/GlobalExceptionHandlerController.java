package ru.technoserv.controller;


import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(EmployeeController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationProblem(MethodArgumentNotValidException e){
        logger.error(e.getMessage());
        List<ObjectError> errors= e.getBindingResult().getAllErrors();
        List<CommonError> myErrors = new ArrayList<>();
        for (ObjectError err: errors
                ) {
            myErrors.add(new CommonError(err.getDefaultMessage()));
        }
        return new ResponseEntity<>(myErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> validationProblem(RuntimeException e){
        logger.error(e.getMessage());
        CommonError error = new CommonError(e.getMessage());
        try{
            String[] errorMessage = e.getMessage().split("!@#");
            String id = errorMessage[0].trim();
            error.setId(new Integer(id));
            error.setMessage(errorMessage[1]);
        }catch (Exception ex){
            logger.info(ex);
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
