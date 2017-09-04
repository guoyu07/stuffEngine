package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.technoserv.dao.Department;
import ru.technoserv.services.DepartmentService;

import java.util.List;

/**
 * Данный контроллер предназначен для обработки запросов, связанных
 * с отделами.
 */

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

    @RequestMapping(method = RequestMethod.POST)
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.DELETE)
    public Department closeDepartment(@PathVariable Integer depId) {
        return departmentService.deleteDepartment(depId);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.PATCH)
    public void changeParentDept(@RequestBody Department newParentDepartment,
                                 @PathVariable Integer depId) {
        departmentService.reassignDepartment(depId, newParentDepartment.getId());
    }

}
