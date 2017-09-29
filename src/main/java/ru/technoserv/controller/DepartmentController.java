package ru.technoserv.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.technoserv.domain.Department;
import ru.technoserv.services.DepartmentService;
import ru.technoserv.services.impl.EmployeeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "department", produces={"application/json; charset=UTF-8"})
public class DepartmentController {

    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(name = "6",value = "/{depId}/subdepts", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getSubDepts(@PathVariable Integer depId, HttpServletRequest request) throws IOException {
        System.out.println(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
        logger.info("Получен request на чтение подразделений отдела с ID: " + depId);
        List<Department> subDepts = departmentService.getSubDepts(depId);
        logger.info("Возвращаемые подразделения: "+ subDepts);
        return new ResponseEntity<>(subDepts, HttpStatus.OK);
    }

    @RequestMapping(name = "7",value = "/{depId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody  ResponseEntity<?> getDept(@PathVariable Integer depId, HttpServletRequest request) {
        logger.info("Получен request на чтение отдела по ID: " + depId);
        Department dep = departmentService.getDepartment(depId);
        logger.info("Возвращаемый отдел: " + dep);
        return new ResponseEntity<>(dep, HttpStatus.OK);
    }

    @RequestMapping(name = "9",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createDepartment(@RequestBody Department department, HttpServletRequest request) {
        logger.info("Получен request на создание отдела: " + department);
        Department dep = departmentService.createDepartment(department);
        logger.info("Созданный отдел: " + dep);
        return new ResponseEntity<>(dep, HttpStatus.CREATED);
    }

    @RequestMapping(name = "10",value = "/{depId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String closeDepartment(@PathVariable Integer depId, HttpServletRequest request) {
        logger.info("Получен request на удаление отдела с ID: " + depId);
        Department dep = departmentService.deleteDepartment(depId);
        logger.info("Удаленный отдел: " + dep);
        return "deleted";
    }

    @RequestMapping(name = "8",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateDept(@RequestBody Department department, HttpServletRequest request) {
        logger.info("Получен request на изменение отдела: " + department);
        Department dep = departmentService.updateDept(department);
        logger.info("Измененный отдел: " + dep);
        return new ResponseEntity<>(dep, HttpStatus.OK);
    }

    @RequestMapping(name = "13", value = "/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllDeps(HttpServletRequest request) {
        logger.info("Получен request на получение списка всех отделов");
        List<Department> allDeps = departmentService.getAllDepartments();
        logger.info("Список всех отделов" + allDeps);
        return new ResponseEntity<>(allDeps, HttpStatus.OK);
    }

}
