package ru.technoserv.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import ru.technoserv.domain.Employee;
import ru.technoserv.domain.EmployeeHistory;
import ru.technoserv.services.EmployeeService;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


/**
 * Данный контроллер предназначен для обработки запросов на работу с
 * сотрудниками.
 */
@RestController
@RequestMapping(value="employee", produces={"application/json; charset=UTF-8"})
public class EmployeeController {

    private static final Logger logger = Logger.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;


    /**
     * Запрос на получение всех сотрудников по ИД отдела
     * @param departmentID ИД отдела
     * @return Список отделов и код ОК
     */
    @RequestMapping(name = "1", value = "/all/{departmentID}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartmentStuff(@PathVariable int departmentID, HttpServletRequest request){
        logger.info("Запрос на получения списка отдела по ид"+ departmentID);
        List<Employee> employeeList = employeeService.getEmployees(departmentID);
        logger.info("Json отдаваемый на запрос получения списка сотрудников"+employeeList);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    /**
     * Запрос на получение сотрудника по ИД
     * @param id ИД сотрудника
     * @return Объект сотрудника и код ОК
     */
    @RequestMapping(name = "2", value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getEmployee(@PathVariable int id, HttpServletRequest request)throws IOException{
        logger.info("Запрос на получение сотрудника по ид"+ id);
        Employee employee = employeeService.getEmployee(id);
        logger.info("Json ответ на получение сотрудника по ид"+ employee);


        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Запрос на создание нового сотрудника
     * @param employee создаваемый сотрудника
     * @return созданного сотрудника с кодом CREATED
     */
    @RequestMapping(name ="4", method = RequestMethod.POST, consumes = {"application/json"} )
    public  ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee, HttpServletRequest request){
        logger.info("Запрос на создание сотрудника"+ employee);
        Employee emp = employeeService.createEmployee(employee);
        logger.info("Json ответ на запрос создания сотрудника"+ emp);
        return new ResponseEntity<>( emp, HttpStatus.CREATED);
    }

    @RequestMapping(name = "3", method = RequestMethod.PUT, consumes = {"application/json"} )
    public ResponseEntity<?> editEmployee(@Valid @RequestBody Employee employee, HttpServletRequest request)throws IOException{
        logger.info("Запрос на изменение сотрудника"+employee);
        Employee emp = employeeService.changeEmployee(employee);
        logger.info("Json твет на изменение сотрудника"+emp);
        return new ResponseEntity<>(emp , HttpStatus.OK);
    }
    /**
     * Удаление сотрудника по заданному ид
     * @param id ид удаляемого сотрудника
     * @return строку об успешном завершении
     */
    @RequestMapping(name = "5", value = "/{id}", method = RequestMethod.DELETE)
    public String employeeDelete(@PathVariable int id, HttpServletRequest request){
        logger.info("Запрос на удаление сотрудника по ид"+id);
        employeeService.removeEmployee(id);
        logger.info("Успешное удаление сотрудника");
        return "deleted";
    }

    @RequestMapping(name = "11", value = "/{id}/history", method = RequestMethod.GET)
    public ResponseEntity<?> employeeHistory(@PathVariable int id, HttpServletRequest request) {
        logger.info("Запрос на получения истории изменений сотрудника с ид"+ id);
        List<EmployeeHistory> history = employeeService.getEmployeeStory(id);
        logger.info("Json отдаваемый на запрос получения истории изменнеий"+history);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @RequestMapping(name = "12", value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEmployees(HttpServletRequest request) {
        logger.info("Получен request на чтение всех сорудников");
        List<Employee> employees = employeeService.getAllEmployees();
        logger.info("Возвращаемый список сотрудников: " + employees);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @RequestMapping(name = "16", value = "/part/{start}/{num}", method = RequestMethod.GET)
    public ResponseEntity<?> getPartEmployees(@PathVariable int start, @PathVariable int num, HttpServletRequest request){
        logger.info("Получен запрос на чтение части сотрудников");
        List<Employee> employees = employeeService.getPartOfEmployeeList(start, num);
        logger.info("Возвращаемый список сотрудников: " + employees);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


}
