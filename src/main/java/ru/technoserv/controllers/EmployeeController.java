package ru.technoserv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    public String name;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getEmployeeData(){
        return "employee";
    }

    @RequestMapping(value="/employee", method=RequestMethod.GET)
    public String getEmployeeInfo(
            @RequestParam("name") String text) {
        //model.addAttribute("name", text);
        //TODO вставить сюда сложную логику создания сотрудника
        this.name = text;
            return text;

    }

}
