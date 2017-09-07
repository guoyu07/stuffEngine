package ru.technoserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.technoserv.dao.Department;
import ru.technoserv.services.DepartmentService;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/{depId}/subdepts", method = RequestMethod.GET,
            name = "получение дочерних отделов")
    public List<Department> getSubDepts(@PathVariable Integer depId, HttpServletRequest request) {
        List<Department> subDepts = departmentService.getSubDepts(depId);

        return subDepts;
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.GET,
            name = "поиск отдела по id")
    public Department getDept(@PathVariable Integer depId, HttpServletRequest request) {
        return departmentService.getDepartment(depId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json",
            name = "создание отдела")
    public Department createDepartment(@RequestBody Department department, HttpServletRequest request) {
        return departmentService.createDepartment(department);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.DELETE,
            name = "удаление отдела")
    public Department closeDepartment(@PathVariable Integer depId, HttpServletRequest request) {
        return departmentService.deleteDepartment(depId);
    }

    @RequestMapping(value = "/{depId}", method = RequestMethod.PATCH,
            name = "переподчинение отдела другому отделу")
    public void changeParentDept(@RequestBody Department newParentDepartment,
                                 @PathVariable Integer depId, HttpServletRequest request) {
        departmentService.reassignDepartment(depId, newParentDepartment.getId());
    }

}
