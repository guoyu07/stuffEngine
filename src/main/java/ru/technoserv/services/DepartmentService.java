package ru.technoserv.services;

import ru.technoserv.dao.Department;

import java.util.List;

public interface DepartmentService {

    Department createDepartment (Department department);

    List<Department> getAllDepartments();

    List<Department> getSubDepts(int deptId);

    void reassignDepartment(int dept, int newParentDept);

    Department deleteDepartment(Department department);
}
