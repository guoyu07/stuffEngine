package ru.technoserv.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 * @author Kondratyev Dmitry
 */
@RestController
public class EmployeeController {

    private Employee employee;

    private static Logger log = Logger.getLogger(EmployeeController.class.getName());
       /**
     * Запрос предназначен для создания работника
     * @param firstName имя
     * @param lastName фамилия
     * @param fatherName отчество
     * @param gender пол
     * @param birthDate дата рождения
     * @param salary зарплата
     * @param jobTitle должность
     * @return Сотрудника, который посылается клиенту в виде JSON объекта
     */
    @RequestMapping("/createEmployee")
    public Employee createEmployee(
            @RequestParam(value="firstName") String firstName,
            @RequestParam(value="lastName") String lastName,
            @RequestParam(value="fatherName") String fatherName,
            @RequestParam(value="gender") String gender,
            @RequestParam(value="birthDate") String birthDate,
            @RequestParam(value="salary") String salary,
            @RequestParam(value="jobTitle") String jobTitle,
            HttpServletRequest request
    ){
        employee = new Employee(firstName, lastName);
        log.info("User"+request.getRemoteAddr()+" are send "+request.getRemoteUser());
        return employee;
    }

    @RequestMapping("/employee/{employeeLastName}")
    public Employee getEmployeeByName(
            @PathVariable("employeeLastName") String lastName,
            Model model,
            HttpServletRequest request){
                log.info("User "+request.getRemoteAddr()+" try found "+lastName);
                //TODO выдавать нужного сотрудника вместо заглушки
                return employee;

    }

}
