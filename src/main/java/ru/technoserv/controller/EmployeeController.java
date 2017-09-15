package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.technoserv.exceptions.InvalidInputException;
import ru.technoserv.dao.Employee;
import ru.technoserv.services.EmployeeService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;


/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 */
@RestController
@RequestMapping(value="/employee")
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
        if(employeeList.size()==0){
            throw new InvalidInputException("");
        }
        return new ResponseEntity<Object>(employeeList, HttpStatus.OK);
    }

    /**
     * Запрос на получение сотрудника по ИД
     * @param id ИД сотрудника
     * @return Объект сотрудника и код ОК
     */
    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee(@PathVariable int id){
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }


    /**
     * Запрос на создание нового сотрудника
     * @param employee создаваемый сотрудника
     * @return созданного сотрудника с кодом CREATED
     */
    @RequestMapping( method = RequestMethod.POST, consumes = {"application/json"} )
    public  ResponseEntity<?> createEmployee( @RequestBody Employee employee){
        return new ResponseEntity<>( employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = {"application/json"} )
    public ResponseEntity<?> editEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.changeEmployee(employee), HttpStatus.OK);
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
}
