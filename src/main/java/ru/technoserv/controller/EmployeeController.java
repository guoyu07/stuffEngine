package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.technoserv.services.EmployeeService;


import java.util.logging.Logger;

/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 * @author Kondratyev Dmitry
 */

@Controller
public class EmployeeController {

    private Employee employee;
   // @Autowired
   // private EmployeeService employeeService;
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
            @RequestParam(value="jobTitle") String jobTitle
    ){
        //employeeService.addEmployee(firstName, lastName);
        //log.info("User"+request.getRemoteAddr()+" are send "+request.getRemoteUser());
        ModelAndView model = new ModelAndView("employee");
        model.addObject("name", lastName);
        return new Employee(firstName, lastName);
    }

    @RequestMapping("/employee/{id}")
    public Employee getEmployeeByName(
            @PathVariable("id") int id,
            Model model){
       // log.info("User "+request.getRemoteAddr()+" try found "+id);
        return new Employee("test","test");
    }

    @RequestMapping("/")
    public ModelAndView welcomeMessage(
            @RequestParam(value = "name", required = false) String name) {
        // Name of your jsp file as parameter
        ModelAndView view = new ModelAndView("employee");
       // view.addObject("name", name);
        return view;
    }

}
