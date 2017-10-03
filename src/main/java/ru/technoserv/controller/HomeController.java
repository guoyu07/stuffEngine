package ru.technoserv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/dept/{depId}")
    public String deptPage(@PathVariable int depId) {
        return "dept_page";
    }

    @RequestMapping(value = "/employees/{empId}")
    public String empPage(@PathVariable int empId) {
        return "employee_page";
    }

    @RequestMapping(value = "/employees/{empId}/history")
    public String historyPage(@PathVariable int empId) {
        return "history";
    }
}
