package ru.technoserv.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.technoserv.dao.Department;
import ru.technoserv.exceptions.*;
import ru.technoserv.services.DepartmentService;

import java.util.List;



@RestController
@RequestMapping(value = "/department", produces={"application/json; charset=UTF-8"})
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/{depId}/subdepts", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getSubDepts(@PathVariable Integer depId) {
        List<Department> subDepts = departmentService.getSubDepts(depId);
        String json = GsonUtility.toJson(subDepts);
        return new ResponseEntity<>(json, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody  ResponseEntity<?> getDept(@PathVariable Integer depId) {
        Department dep = departmentService.getDepartment(depId);
        String json = GsonUtility.toJson(dep);
        return new ResponseEntity<>(json, HttpStatus.FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        Department dep = departmentService.createDepartment(department);
        String json = GsonUtility.toJson(dep);
        return new ResponseEntity<>(json, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> closeDepartment(@PathVariable Integer depId) {
        Department dep = departmentService.deleteDepartment(depId);
        String json = GsonUtility.toJson(dep);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateDept(@RequestBody Department department) {
        Department dep = departmentService.updateDept(department);
        String json = GsonUtility.toJson(dep);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonError> commonException(CommonException e){
        return new ResponseEntity<CommonError>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentException.class)
    public ResponseEntity<CommonError> departmnetException(DepartmentException e){
        return new ResponseEntity<CommonError>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<CommonError> notFound(DepartmentNotFoundException e){
        return new ResponseEntity<CommonError>(new CommonError(e.getErrorId(), e.getShortMessage()), HttpStatus.NOT_FOUND);
    }

}
