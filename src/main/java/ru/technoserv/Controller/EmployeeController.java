package ru.technoserv.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 * @author Kondratyev Dmitry
 */
@RestController
public class EmployeeController {


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
    @RequestMapping("/employee")
    public Employee employee(
            @RequestParam(value="firstName") String firstName,
            @RequestParam(value="lastName") String lastName,
            @RequestParam(value="fatherName") String fatherName,
            @RequestParam(value="gender") String gender,
            @RequestParam(value="birthDate") String birthDate,
            @RequestParam(value="Salary") String salary,
            @RequestParam(value = "jobTitle") String jobTitle
    ){
        return new Employee(firstName, lastName);
    }

}
