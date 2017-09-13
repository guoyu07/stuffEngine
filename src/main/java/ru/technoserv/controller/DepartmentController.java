package ru.technoserv.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.technoserv.dao.Department;
import ru.technoserv.exceptions.*;
import ru.technoserv.services.DepartmentService;
import ru.technoserv.services.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping(value = "/department", produces={"application/json; charset=UTF-8"})
public class DepartmentController {

    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/{depId}/subdepts", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getSubDepts(@PathVariable Integer depId) {
        logger.info("Запрос на получение подотделов отдеал с ид "+ depId);
        List<Department> subDepts = departmentService.getSubDepts(depId);
        String json = GsonUtility.toJson(subDepts);
        logger.info("Json ответ на получение подотделов"+ json);
        return new ResponseEntity<>(json, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody  ResponseEntity<?> getDept(@PathVariable Integer depId) {
        logger.info("Получение отдела по ид "+depId);
        Department dep = departmentService.getDepartment(depId);
        String json = GsonUtility.toJson(dep);
        logger.info("Json ответ на получение отдела по id"+ json);
        return new ResponseEntity<>(json, HttpStatus.FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        logger.info("Запрос на создание отдела"+department);
        Department dep = departmentService.createDepartment(department);
        String json = GsonUtility.toJson(dep);
        logger.info("Json ответ на создание отдела"+ json);
        return new ResponseEntity<>(json, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> closeDepartment(@PathVariable Integer depId) {
        logger.info("Закрытие отдела с ид "+depId);
        Department dep = departmentService.deleteDepartment(depId);
        String json = GsonUtility.toJson(dep);
        logger.info("Json ответ на закрытие отдела"+json);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateDept(@RequestBody Department department) {
        logger.info("Запрос на изменение отдела"+department);
        Department dep = departmentService.updateDept(department);
        String json = GsonUtility.toJson(dep);
        logger.info("Ответ на изменение отдела"+json);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonError> commonException(CommonException e){
        logger.error(e.getShortMessage());
        return new ResponseEntity<>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentException.class)
    public ResponseEntity<CommonError> departmnetException(DepartmentException e){
        logger.error(e.getShortMessage());
        return new ResponseEntity<>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<CommonError> notFound(DepartmentNotFoundException e){
        logger.error(e.getShortMessage());
        return new ResponseEntity<>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.NOT_FOUND);
    }
}
