package ru.technoserv.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.technoserv.domain.Employee;
import ru.technoserv.exceptions.*;
import ru.technoserv.services.EmployeeService;

import javax.validation.Valid;
import java.util.List;


/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 */
@RestController
@RequestMapping(value="/employee", produces={"application/json; charset=UTF-8"})
public class EmployeeController {

    private static final Logger logger = Logger.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Запрос на получение всех сотрудников по ИД отдела
     * @param departmentID ИД отдела
     * @return Список отделов и код ОК
     */
    @RequestMapping(value = "/all/{departmentID}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartmentStuff(@PathVariable int departmentID){
        logger.info("Запрос на получения списка отдела по ид"+ departmentID);
        List<Employee> employeeList = employeeService.getEmployees(departmentID);
        String json = GsonUtility.toJson(employeeList);
        logger.info("Json отдаваемый на запрос получения списка сотрудников"+json);
        return new ResponseEntity<Object>(json, HttpStatus.OK);
    }

    /**
     * Запрос на получение сотрудника по ИД
     * @param id ИД сотрудника
     * @return Объект сотрудника и код ОК
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getEmployee(@PathVariable int id){
        logger.info("Запрос на получение сотрудника по ид"+ id);
        Employee employee = employeeService.getEmployee(id);
        String json = GsonUtility.toJson(employee);
        logger.info("Json ответ на получение сотрудника по ид"+ json);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    /**
     * Запрос на создание нового сотрудника
     * @param employee создаваемый сотрудника
     * @return созданного сотрудника с кодом CREATED
     */
    @RequestMapping( method = RequestMethod.POST, consumes = {"application/json"} )
    public  ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee){
        logger.info("Запрос на создание сотрудника"+ employee);
        String json = GsonUtility.toJson(employeeService.createEmployee(employee));
        logger.info("Json ответ на запрос создания сотрудника"+ json);
        return new ResponseEntity<>( json, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = {"application/json"} )
    public ResponseEntity<?> editEmployee(@Valid @RequestBody Employee employee){
        logger.info("Запрос на изменение сотрудника"+employee);
        String json = GsonUtility.toJson(employeeService.changeEmployee(employee));
        logger.info("Json твет на изменение сотрудника"+json);
        return new ResponseEntity<>(json , HttpStatus.OK);
    }
    /**
     * Удаление сотрудника по заданному ид
     * @param id ид удаляемого сотрудника
     * @return строку об успешном завершении
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String employeeDelete(@PathVariable int id){
        logger.info("Запрос на удаление сотрудника по ид"+id);
        employeeService.removeEmployee(id);
        logger.info("Успешное удаление сотрудника");
        return "delete";
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonError> commonException(CommonException e){
        logger.error(e.getShortMessage());
        return new ResponseEntity<CommonError>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<CommonError> employeeException(EmployeeException e){
        logger.error(e.getShortMessage());
        return new ResponseEntity<>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<CommonError> notFound(EmployeeNotFoundException e){
        logger.error(e.getShortMessage());
        return new ResponseEntity<>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeTheHeadOfDepartment.class)
    public ResponseEntity<CommonError> headException(EmployeeTheHeadOfDepartment e){
        logger.error(e.getShortMessage());
        return new ResponseEntity<>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }

}
