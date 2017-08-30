package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.technoserv.controller.JSON.Request.EmployeeRequest;
import ru.technoserv.dao.Employee;
import ru.technoserv.services.EmployeeService;

import javax.servlet.http.HttpServletResponse;


/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 * @author Kondratyev Dmitry
 */
@RestController
@RequestMapping(value="/employee", consumes = {"application/json"})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST)
    public String createEmployee(@RequestBody EmployeeRequest employeeRequest, HttpServletResponse response
    ){
        try {
            employeeService.createEmployee(employeeRequest);
        }
        catch (Exception e) {
            try {
                response.sendError(555, e.getMessage());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return "newUser";
    }

    @RequestMapping(method = RequestMethod.GET)
    public Employee getEmployee(@RequestBody EmployeeRequest employeeRequest, HttpServletResponse response
    ){
        try {
            employeeService.getEmployeeStory(employeeRequest);
        }
        catch (Exception e) {
            try {
                response.sendError(555, e.getMessage());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return new Employee();
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public String changeEmployeeParam(@RequestBody EmployeeRequest employeeRequest, HttpServletResponse response
    ){
        try {
            employeeService.changeEmployeeData(employeeRequest);
        }
        catch (Exception e) {
            try {
                response.sendError(555, e.getMessage());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return "changed";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteEmployee(@RequestBody EmployeeRequest employeeRequest, HttpServletResponse response
    ){
        try {
            employeeService.removeEmployee(employeeRequest);
        }
        catch (Exception e) {
            try {
                response.sendError(555, e.getMessage());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return "WASTED";
    }
}
