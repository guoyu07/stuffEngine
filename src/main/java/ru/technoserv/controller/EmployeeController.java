package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.technoserv.dao.Employee;
import ru.technoserv.services.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 * @author Kondratyev Dmitry
 */
@RestController
@RequestMapping(value="/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/{department}", method = RequestMethod.GET)
    public List<Employee> getDepartmentStuff(@PathVariable String department){
        List<Employee> employeeList = employeeService.getEmployees(department);
        if(employeeList.size()==0){throw new RuntimeException("There are not employees in department or department name is incorrect!");
        }
        return employeeList;
    }

    @RequestMapping(value="/newEmployee", method = RequestMethod.PUT,  consumes = {"application/json"} )
    public String createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
        return "AddNeUser";
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.PATCH, consumes = {"application/json"})
    public String employeeTransfer(@RequestBody Employee employee){
        employeeService.transferEmployee(employee);
        return "transfer";
    }

    @RequestMapping(value = "/salary", method = RequestMethod.PATCH, consumes = {"application/json"})
    public String employeeChangeSalary(@RequestBody Employee employee){
        employeeService.changeEmployeeSalary(employee);
        return "salary";
    }

    @RequestMapping(value = "/grade", method = RequestMethod.PATCH, consumes = {"application/json"})
    public String employeeChangeGrade(@RequestBody Employee employee){
        employeeService.changeEmployeeGrade(employee);
        return "grade";
    }

    @RequestMapping(value = "/position", method = RequestMethod.PATCH, consumes = {"application/json"})
    public String employeeChangePosition(@RequestBody Employee employee){
        employeeService.changeEmployeePosition(employee);
        return "position";
    }

    @RequestMapping(value = "/quit/{id}", method = RequestMethod.DELETE)
    public String employeeDelete(@PathVariable int id){
        employeeService.removeEmployee(id);
        return "delete";
    }

}
