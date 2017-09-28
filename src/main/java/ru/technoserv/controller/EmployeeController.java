package ru.technoserv.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.technoserv.domain.Employee;
import ru.technoserv.domain.EmployeeHistory;
import ru.technoserv.exceptions.*;
import ru.technoserv.services.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


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
    @RequestMapping(name = "1", value = "/all/{departmentID}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartmentStuff(@PathVariable int departmentID, HttpServletRequest request){
        logger.info("Запрос на получения списка отдела по ид"+ departmentID);
        List<Employee> employeeList = employeeService.getEmployees(departmentID);
        logger.info("Json отдаваемый на запрос получения списка сотрудников"+employeeList);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    /**
     * Запрос на получение сотрудника по ИД
     * @param id ИД сотрудника
     * @return Объект сотрудника и код ОК
     */
    @RequestMapping(name = "2", value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getEmployee(@PathVariable int id, HttpServletRequest request)throws IOException{
        logger.info("Запрос на получение сотрудника по ид"+ id);
        System.out.println(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
        Employee employee = employeeService.getEmployee(id);
        logger.info("Json ответ на получение сотрудника по ид"+ employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Запрос на создание нового сотрудника
     * @param employee создаваемый сотрудника
     * @return созданного сотрудника с кодом CREATED
     */
    @RequestMapping(name ="4", method = RequestMethod.POST, consumes = {"application/json"} )
    public  ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee, HttpServletRequest request){
        logger.info("Запрос на создание сотрудника"+ employee);
        Employee emp = employeeService.createEmployee(employee);
        logger.info("Json ответ на запрос создания сотрудника"+ emp);
        return new ResponseEntity<>( emp, HttpStatus.CREATED);
    }

    @RequestMapping(name = "3", method = RequestMethod.PUT, consumes = {"application/json"} )
    public ResponseEntity<?> editEmployee(@Valid @RequestBody Employee employee, HttpServletRequest request)throws IOException{
        logger.info("Запрос на изменение сотрудника"+employee);
        Employee emp = employeeService.changeEmployee(employee);
        logger.info("Json твет на изменение сотрудника"+emp);
        return new ResponseEntity<>(emp , HttpStatus.OK);
    }
    /**
     * Удаление сотрудника по заданному ид
     * @param id ид удаляемого сотрудника
     * @return строку об успешном завершении
     */
    @RequestMapping(name = "5", value = "/{id}", method = RequestMethod.DELETE)
    public String employeeDelete(@PathVariable int id, HttpServletRequest request){
        logger.info("Запрос на удаление сотрудника по ид"+id);
        employeeService.removeEmployee(id);
        logger.info("Успешное удаление сотрудника");
        return "delete";
    }

    @RequestMapping(name = "11", value = "/{id}/history", method = RequestMethod.GET)
    public ResponseEntity<?> employeeHistory(@PathVariable int id, HttpServletRequest request) {
        logger.info("Запрос на получения истории изменений сотрудника с ид"+ id);
        List<EmployeeHistory> history = employeeService.getEmployeeStory(id);
        logger.info("Json отдаваемый на запрос получения истории изменнеий"+history);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @RequestMapping(name = "12", value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEmployees(HttpServletRequest request) {
        logger.info("Запрос на получения всех сорудников");
        List<Employee> employees = employeeService.getAllEmployees();
        logger.info("Json отдаваемый на запрос получения всех сотрудников"+employees);
        return new ResponseEntity<>(employees, HttpStatus.OK);
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
