package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.technoserv.dao.Employee;
import ru.technoserv.repository.EmployeeRepository;
import ru.technoserv.services.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


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
            @RequestParam(value="lastName") String lastName, HttpServletRequest request, HttpServletResponse response
    ){
        try {
            employeeService.addEmployee(firstName, lastName);
        }
        catch (Exception e) {
            try {
                response.sendError(502, "Не удалось добавить пользователя");
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return "newUser";
    }

    /**
     * Получение сотрудника по имени и фамилии
     * @param lastName фамилия искомого сотрудника
     * @param firstName имя искомого сотрудника
     * @return JSON объект клиенту сделавшему запрос
     */
    @RequestMapping("/employee/{lastName}/{firstName}")
    public Employee getEmployeeByName(
            @PathVariable("lastName") String lastName,
            @PathVariable("firstName") String firstName,
            HttpServletResponse response){
        Employee emp = new Employee();
        try{
            emp = employeeRepository.getEmployee(firstName, lastName);
        }catch (RuntimeException e){
            try {
                response.sendError(501, e.getMessage());
            }catch (Exception e1){

            }
        }
        return emp;
    }

    /**
     * Приветсвенное сообщение
     * @return приветствие
     */
    @RequestMapping("/")
    public String welcomeMessage() {
        return "Hello, I'm working!";
    }

}
