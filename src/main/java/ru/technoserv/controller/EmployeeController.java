package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.technoserv.dao.Employee;
import ru.technoserv.repository.EmployeeRepository;
import ru.technoserv.services.EmployeeService;

import javax.servlet.http.HttpServletRequest;


/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 * @author Kondratyev Dmitry
 */

@RestController
public class EmployeeController {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Запрос предназначен для создания работника
     * @param firstName имя
     * @param lastName фамилия
     * @return Сотрудника, который посылается клиенту в виде JSON объекта
     */
    @RequestMapping(value="/employee", method= RequestMethod.POST)
    public String createEmployee(
            @RequestParam(value="firstName") String firstName,
            @RequestParam(value="lastName") String lastName, HttpServletRequest request
    ){
        employeeService.addEmployee(firstName, lastName);
        return "newUser";
    }

    @RequestMapping("/employee/{lastName}/{firstName}")
    public Employee getEmployeeByName(
            @PathVariable("lastName") String lastName,
            @PathVariable("firstName") String firstName){
        return employeeRepository.getEmployee(firstName, lastName);
    }

    @RequestMapping("/")
    public String welcomeMessage() {
        return "employee";
    }

}
