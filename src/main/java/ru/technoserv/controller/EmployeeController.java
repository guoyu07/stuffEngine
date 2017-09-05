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
    public Employee getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);
    }

    /**
     * Запрос на прием нового сотрудника
     * @param employee принимаемый сотрудник
     * @return строку об успешном завершении
     */
    @RequestMapping(value="/newEmployee", method = RequestMethod.PUT )
    public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            System.out.println("Error");
            throw new IllegalArgumentException();
        }
        Employee e =  employeeService.createEmployee(employee);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }



    /**
     * Запрос на перевод сотрудника в другой отдел
     * @return строку с информации об успешном завершении
     */
    @RequestMapping(value = "/transfer_{empID}/to_{depID}", method = RequestMethod.PATCH)
    public String employeeTransfer(@PathVariable int empID, @PathVariable int depID){
        employeeService.transferEmployee(empID, depID);
        return "transfer";
    }

    /**
     * Запрос на изменение зарплаты сотрудника
     * @param employee экземпляр сотрудника с изменяемыми полями
     * @return строку об успешном завершении
     */
    @RequestMapping(value = "/salary", method = RequestMethod.PATCH, consumes = {"application/json"})
    public String employeeChangeSalary(@RequestBody Employee employee){
        employeeService.changeEmployeeSalary(employee);
        return "salary";
    }

    /**
     * Изменение грейда сотрудника
     * @param employee экземпляр сотрудника с изменяемыми полями
     * @return строку об успешном завершении
     */
    @RequestMapping(value = "/grade", method = RequestMethod.PATCH, consumes = {"application/json"})
    public String employeeChangeGrade(@RequestBody Employee employee){
        employeeService.changeEmployeeGrade(employee);
        return "grade";
    }

    /**
     * Изменение должности сотрудника
     * @param employee экземпляр с изменяемыми полями
     * @return строка об успешном завершении
     */
    @RequestMapping(value = "/position", method = RequestMethod.PATCH, consumes = {"application/json"})
    public String employeeChangePosition(@RequestBody Employee employee){
        employeeService.changeEmployeePosition(employee);
        return "position";
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
