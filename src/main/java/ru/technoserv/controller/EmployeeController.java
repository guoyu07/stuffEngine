package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.technoserv.exceptions.InvalidInputException;
import ru.technoserv.dao.Employee;
import ru.technoserv.services.EmployeeService;

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
     * @param department название отдела
     * @return список сотрудников отдела
     */
    @RequestMapping(value = "/all/{department}", method = RequestMethod.GET)
    public List<Employee> getDepartmentStuff(@PathVariable String department){
        List<Employee> employeeList = employeeService.getEmployees(department);
        if(employeeList.size()==0){throw new InvalidInputException("");
        }
        return employeeList;
    }
boolean a = true;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getDepartmentStuff(@PathVariable int id){
        if(a) {
            throw new InvalidInputException("Wrong input");
        }
        return employeeService.getEmployee(id);
    }

    /**
     * Запрос на прием нового сотрудника
     * @param employee принимаемый сотрудник
     * @return строку об успешном завершении
     */
    @RequestMapping(value="/newEmployee", method = RequestMethod.PUT,  consumes = {"application/json"} )
    public String createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
        return "AddNewUser";
    }


    /**
     * Запрос на перевод сотрудника в другой отдел
     * @param employee Экземпляр сотрудника с изменяемыми полями
     * @return строку с информации об успешном завершении
     */
    @RequestMapping(value = "/transfer", method = RequestMethod.PATCH, consumes = {"application/json"})
    public String employeeTransfer(@RequestBody Employee employee){
        System.out.println(employee);
        employeeService.transferEmployee(employee);
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
