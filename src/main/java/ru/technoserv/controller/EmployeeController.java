package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.Controller;
import ru.technoserv.services.EmployeeService;



/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 * @author Kondratyev Dmitry
 */

@RestController
public class EmployeeController implements MyController {


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
            @RequestParam(value="lastName") String lastName
    ){
        employeeService.addEmployee(firstName, lastName);
        return "newUser";
    }

    @RequestMapping("/employee/{id}")
    public Employee getEmployeeByName(
            @PathVariable("id") int id){
        return employeeService.getEmployee(id);
    }

    @RequestMapping("/")
    public String welcomeMessage() {
        return "employee";
    }

}
