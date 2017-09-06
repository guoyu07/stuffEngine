package ru.technoserv.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.technoserv.exceptions.InvalidInputException;
import ru.technoserv.dao.Employee;
import ru.technoserv.services.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;


/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 */
@RestController
@RequestMapping(value="/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Запрос на получение всех сотрудников по ИД отдела
     * @param departmentID ИД отдела
     * @return Список отделов и код ОК
     */
    @RequestMapping(value = "/all/{departmentID}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartmentStuff(@PathVariable int departmentID){
        List<Employee> employeeList = employeeService.getEmployees(departmentID);
        if(employeeList.size()==0){
            throw new InvalidInputException("");
        }
        return new ResponseEntity<Object>(employeeList, HttpStatus.OK);
    }

    /**
     * Запрос на получение сотрудника по ИД
     * @param id ИД сотрудника
     * @return Объект сотрудника и код ОК
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee(@PathVariable int id){
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }


    /**
     * Запрос на создание нового сотрудника
     * @param employee создаваемый сотрудника
     * @param bindingResult результат валидации
     * @return созданного сотрудника с кодом CREATED
     */
    @RequestMapping( method = RequestMethod.POST, consumes = {"application/json"} )
    public  ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            System.out.println("Error");
            return new ResponseEntity<>( new IllegalArgumentException(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>( employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    /**
     * Запрос на перевод сотрудника в другой отдел
     * @param empID ИД сотрудника
     * @param depID ИД отдела в который переводят
     * @return измененный объект сотрудника с кодом ОК
     */
    @RequestMapping(value = "/{empID}/department/{depID}", method = RequestMethod.PATCH)
    public ResponseEntity<?> employeeTransfer(@PathVariable int empID, @PathVariable int depID){
        return new ResponseEntity<>(employeeService.transferEmployee(empID, depID), HttpStatus.OK);
    }

    /**
     * Запрос на изменение зарплаты сотрудника
     * @param empID ИД сотрудника
     * @param newSalary новое значение зарплаты
     * @return измененный объект сотрудника с кодом ОК
     */
    @RequestMapping(value = "{empID}/salary/{newSalary}", method = RequestMethod.PATCH)
    public ResponseEntity<?> employeeChangeSalary(@PathVariable int empID, @PathVariable BigDecimal newSalary){
        return new ResponseEntity<>( employeeService.changeEmployeeSalary(empID, newSalary), HttpStatus.OK);
    }

    /**
     * Запрос на изменение грейда сотрудника
     * @param empID ИД сотрудника
     * @param newGradeID ИД грейда
     * @return измененный объект сотрудника с кодом ОК
     */
    @RequestMapping(value = "{empID}/grade/{newGradeID}", method = RequestMethod.PATCH)
    public ResponseEntity<?> employeeChangeGrade(@PathVariable int empID, @PathVariable int newGradeID){
        return new ResponseEntity<>( employeeService.changeEmployeeGrade(empID, newGradeID), HttpStatus.OK);
    }

    /**
     * Запрос на изменение долности сотрудника
     * @param empID ИД сотрудника
     * @param newPositionID ИД должности
     * @return измененный объект сотрудника с кодом ОК
     */
    @RequestMapping(value = "{empID}/position/{newPositionID}", method = RequestMethod.PATCH)
    public ResponseEntity<?> employeeChangePosition(@PathVariable int empID, @PathVariable int newPositionID){
        return new ResponseEntity<>(employeeService.changeEmployeePosition(empID, newPositionID), HttpStatus.OK);
    }

    /**
     * Удаление сотрудника по заданному ид
     * @param id ид удаляемого сотрудника
     * @return строку об успешном завершении
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String employeeDelete(@PathVariable int id){
        employeeService.removeEmployee(id);
        return "delete";
    }
}
