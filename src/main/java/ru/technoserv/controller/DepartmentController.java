package ru.technoserv.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.technoserv.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.technoserv.dao.Department;
import ru.technoserv.services.DepartmentService;

import java.util.List;



@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/{depId}/subdepts", method = RequestMethod.GET)
    public ResponseEntity<List<Department>> getSubDepts(@PathVariable Integer depId) {
        List<Department> subDepts = departmentService.getSubDepts(depId);

        return new ResponseEntity<>(subDepts, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.GET)
    public ResponseEntity<Department> getDept(@PathVariable Integer depId) {
        Department dep = departmentService.getDepartment(depId);
        return new ResponseEntity<>(dep, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department dep = departmentService.createDepartment(department);
        return new ResponseEntity<>(dep, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.DELETE)
    public ResponseEntity<Department> closeDepartment(@PathVariable Integer depId) {
        Department dep = departmentService.deleteDepartment(depId);

        return new ResponseEntity<>(dep, HttpStatus.OK);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.PATCH)
    public ResponseEntity<Department> changeParentDept(@RequestBody Department newParentDepartment,
                                                       @PathVariable Integer depId) {
        Department dep = departmentService.reassignDepartment(depId, newParentDepartment.getParentDeptId());

        return new ResponseEntity<>(dep, HttpStatus.OK);
    }

}
