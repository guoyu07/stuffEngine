package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.technoserv.dao.Employee;
import ru.technoserv.services.EmployeeService;

@Controller
public class HomeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/{id}")
    public Employee getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "hello";
    }
}

