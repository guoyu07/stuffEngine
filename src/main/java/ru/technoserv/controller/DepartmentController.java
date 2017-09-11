package ru.technoserv.controller;

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
    public List<Department> getSubDepts(@PathVariable Integer depId) {
        List<Department> subDepts = departmentService.getSubDepts(depId);

        return subDepts;
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.GET)
    public Department getDept(@PathVariable Integer depId) {
        return departmentService.getDepartment(depId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.DELETE)
    public Department closeDepartment(@PathVariable Integer depId) {
        return departmentService.deleteDepartment(depId);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.PATCH)
    public Department changeParentDept(@RequestBody Department newParentDepartment,
                                 @PathVariable Integer depId) {
        return departmentService.reassignDepartment(depId, newParentDepartment.getId());
    }

}
