package ru.technoserv.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.technoserv.exceptions.InvalidInputException;
import ru.technoserv.dao.Employee;
import ru.technoserv.services.EmployeeService;

import javax.servlet.http.HttpServletResponse;
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
     * Получение списка сотрудников по названию
     * @param departmentID  id отдела
     * @return список сотрудников отдела
     */
    @RequestMapping(value = "/all/{departmentID}", method = RequestMethod.GET)
    public List<Employee> getDepartmentStuff(@PathVariable int departmentID){
        List<Employee> employeeList = employeeService.getEmployees(departmentID);
        if(employeeList.size()==0){throw new InvalidInputException("");
        }
        return employeeList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee(@PathVariable int id){
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    /**
     * Запрос на прием нового сотрудника
     * @param employee принимаемый сотрудник
     * @return строку об успешном завершении
     */
    @ResponseBody
    @RequestMapping(value="/newEmployee", method = RequestMethod.PUT, consumes = {"application/json"} )
    public  ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()) {
            System.out.println("Error");
            return new ResponseEntity<>( new IllegalArgumentException(), HttpStatus.BAD_REQUEST);
        }
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        return new ResponseEntity<>( employeeService.createEmployee(employee), HttpStatus.CREATED);
    }



    /**
     * Запрос на перевод сотрудника в другой отдел
     * @return строку с информации об успешном завершении
     */
    @RequestMapping(value = "/{empID}/department/{depID}", method = RequestMethod.PATCH)
    public ResponseEntity<?> employeeTransfer(@PathVariable int empID, @PathVariable int depID, HttpServletResponse response){
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        return new ResponseEntity<>(employeeService.transferEmployee(empID, depID), HttpStatus.OK);
    }

    /**
     * Запрос на изменение зарплаты сотрудника
     * @param employee экземпляр сотрудника с изменяемыми полями
     * @return строку об успешном завершении
     */
    @RequestMapping(value = "{empID}/salary/{newSalary}", method = RequestMethod.PATCH)
    public ResponseEntity<?> employeeChangeSalary(@PathVariable int empID, @PathVariable BigDecimal newSalary, HttpServletResponse response){
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        return new ResponseEntity<>( employeeService.changeEmployeeSalary(empID, newSalary), HttpStatus.OK);
    }

    /**
     * Изменение грейда сотрудника
     * @param employee экземпляр сотрудника с изменяемыми полями
     * @return строку об успешном завершении
     */
    @RequestMapping(value = "{empID}/grade/{newGradeID}", method = RequestMethod.PATCH)
    public ResponseEntity<?> employeeChangeGrade(@PathVariable int empID, @PathVariable int newGradeID, HttpServletResponse response){
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        return new ResponseEntity<>( employeeService.changeEmployeeGrade(empID, newGradeID), HttpStatus.OK);
    }

    /**
     * Изменение должности сотрудника
     * @param employee экземпляр с изменяемыми полями
     * @return строка об успешном завершении
     */
    @RequestMapping(value = "{empID}/position/{newPositionID}", method = RequestMethod.PATCH)
    public ResponseEntity<?> employeeChangePosition(@PathVariable int empID, @PathVariable int newPositionID, HttpServletResponse response){
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        return new ResponseEntity<>(employeeService.changeEmployeePosition(empID, newPositionID), HttpStatus.OK);
    }

    /**
     * Удаление сотрудника по заданному ид
     * @param id ид удаляемого сотрудника
     * @return строку об успешном завершении
     */
    @RequestMapping(value = "/quit/{id}", method = RequestMethod.DELETE)
    public String employeeDelete(@PathVariable int id){
        employeeService.removeEmployee(id);
        return "delete";
    }
}
