package ru.technoserv.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import ru.technoserv.dao.Employee;
import ru.technoserv.exceptions.*;
import ru.technoserv.services.EmployeeService;

import javax.jws.WebService;
import javax.validation.Valid;
import javax.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.List;


/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 */
@RestController
@RequestMapping(value="/employee", produces={"application/json; charset=UTF-8"})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Запрос на получение всех сотрудников по ИД отдела
     * @param departmentID ИД отдела
     * @return Список отделов и код ОК
     */
    @RequestMapping(value = "/all/{departmentID}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartmentStuff(@PathVariable int departmentID){
        List<Employee> employeeList = employeeService.getEmployees(departmentID);
        String json = GsonUtility.toJson(employeeList);
        return new ResponseEntity<Object>(json, HttpStatus.OK);
    }

    /**
     * Запрос на получение сотрудника по ИД
     * @param id ИД сотрудника
     * @return Объект сотрудника и код ОК
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);
        String json = GsonUtility.toJson(employee);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }


    /**
     * Запрос на создание нового сотрудника
     * @param employee создаваемый сотрудника
     * @return созданного сотрудника с кодом CREATED
     */
    @RequestMapping( method = RequestMethod.POST, consumes = {"application/json"} )
    public  ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee){

        String json = GsonUtility.toJson(employeeService.createEmployee(employee));
        return new ResponseEntity<>( json, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = {"application/json"} )
    public ResponseEntity<?> editEmployee(@Valid @RequestBody Employee employee){
        String json = GsonUtility.toJson(employeeService.changeEmployee(employee));
        return new ResponseEntity<>(json , HttpStatus.OK);
    }
    /**
     * Удаление сотрудника по заданному ид
     * @param id ид удаляемого сотрудника
     * @return строку об успешном завершении
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String employeeDelete(@PathVariable int id){
        employeeService.removeEmployee(id);
        return "delete";
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonError> commonException(CommonException e){
        return new ResponseEntity<CommonError>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<CommonError> employeeException(EmployeeException e){
        return new ResponseEntity<>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<CommonError> notFound(EmployeeNotFoundException e){
        return new ResponseEntity<>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeTheHeadOfDepartment.class)
    public ResponseEntity<CommonError> headException(EmployeeTheHeadOfDepartment e){
        return new ResponseEntity<>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }



}
