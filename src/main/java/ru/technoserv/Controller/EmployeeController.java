package ru.technoserv.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @RequestMapping("/employee")
    public Employee employee(
            @RequestParam(value="firstName") String firstName,
            @RequestParam(value="lastName") String lastName
    ){
        return new Employee(firstName, lastName);
    }

}
