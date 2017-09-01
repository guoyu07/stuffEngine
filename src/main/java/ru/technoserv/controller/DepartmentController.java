package ru.technoserv.controller;

import org.springframework.web.bind.annotation.*;
import ru.technoserv.dao.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный контроллер предназначен для обработки запросов, связанных
 * с отделами.
 */

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    @RequestMapping(value = "/{depId}/descendents", method = RequestMethod.GET)
    public List<Department> getDescendents(@PathVariable Long depId) {
        List<Department> descendents = new ArrayList<>();

        return descendents;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createDepartment(@RequestBody Department department) {

    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.DELETE)
    public void closeDepartment(@PathVariable Long depId) {

    }

    @RequestMapping(value = "/{newParentDepId}", method = RequestMethod.PATCH)
    public void changeParentDept(@RequestBody Department department,
                                 @PathVariable Long newParentDepId) {

    }

}
