package ru.technoserv.services;

import ru.technoserv.domain.DepWithChildren;
import ru.technoserv.domain.Department;
import ru.technoserv.domain.Employee;
import ru.technoserv.services.impl.DepartmentServiceImpl;

import java.util.List;

public interface DepartmentService {

    Department createDepartment (Department department);

    Department getDepartment(int deptID);

    List<Department> getAllDepartments();

    List<Department> getSubDepts(int deptId);

    Department updateDept(Department department);

    Department deleteDepartment(int deptID);

    List<DepWithChildren> getHierarchy();

}
