package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.technoserv.controller.JSON.Request.EmployeeRequest;
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

    /**
     * Запрос на приема сотрудника на работу. Предполагается что сотрудник ранее не работал в компании.
     * Метод создате сотрудника и добавляет его в базу как нового сотрудника.
     * @return Сотрудника, который посылается клиенту в виде JSON объекта
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createEmployee(@RequestBody EmployeeRequest employeeRequest, HttpServletResponse response
    ){
        String answer ="error";
        try {
            switch (employeeRequest.getAction()){
                case "create":
                    actionCreate(employeeRequest);
                    break;
                case "getHistory":
                    answer = "getHistory";
                    break;
                case "transferTo":
                    answer = "transferTo";
                    break;
                case "change":
                    answer = "change";
                    break;
                case "delete":
                    answer = "delete";
                    break;
                default: throw new Exception("Invalid JSON param 'action'");
            }
        }
        catch (Exception e) {
            try {
                response.sendError(555, e.getMessage());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return answer;
    }

    private void actionCreate(EmployeeRequest request){
        employeeService.createEmployee(request);
    }




}
