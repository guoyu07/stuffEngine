package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.technoserv.dao.Department;
import ru.technoserv.services.DepartmentService;

import java.util.List;



@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;
    @CrossOrigin
    @RequestMapping(value = "/{depId}/subdepts", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Department>> getSubDepts(@PathVariable Integer depId) {
        List<Department> subDepts = departmentService.getSubDepts(depId);

        return new ResponseEntity<>(subDepts, HttpStatus.FOUND);
    }

    @CrossOrigin
    @RequestMapping(value = "/{depId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> getDept(@PathVariable Integer depId) {
        Department dep = departmentService.getDepartment(depId);
        return new ResponseEntity<>(dep, HttpStatus.FOUND);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department dep = departmentService.createDepartment(department);
        return new ResponseEntity<>(dep, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/{depId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> closeDepartment(@PathVariable Integer depId) {
        Department dep = departmentService.deleteDepartment(depId);

        return new ResponseEntity<>(dep, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> updateDept(@RequestBody Department department) {
        Department dep = departmentService.updateDept(department);

        return new ResponseEntity<>(dep, HttpStatus.OK);
    }

}
