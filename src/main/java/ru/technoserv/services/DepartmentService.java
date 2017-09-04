package ru.technoserv.services;

import ru.technoserv.dao.Department;

import java.util.List;

public interface DepartmentService {

    Department createDepartment (Department department);

    Department getDepartment(int deptID);

    List<Department> getAllDepartments();

    List<Department> getSubDepts(int deptId);

    void reassignDepartment(int deptID, Integer newParentDeptID);

    Department deleteDepartment(int deptID);
}
