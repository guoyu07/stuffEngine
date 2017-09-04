package ru.technoserv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.technoserv.dao.Department;
import ru.technoserv.dao.DepartmentDao;
import ru.technoserv.dao.EmployeeDao;

import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan("ru")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    EmployeeDao employeeDao;
    private boolean isIDLoaded = false;

    @Override
    public Department createDepartment(Department department) {
        if (!isIDLoaded) {
            Department.setGlobalID(departmentDao.getGlobalID);
            isIDLoaded = true;
        }
        department.setId(Department.getGlobalID);
        departmentDao.create(department);
        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> allEmps;
        allEmps = departmentDao.getDepartmentsList();
        return allEmps;
    }

    @Override
    public List<Department> getSubDepts(int deptId) {
        return null;
    }

    @Override
    public void reassignDepartment(int dept, int newParentDept) {

    }

    @Override
    public Department deleteDepartment(Department department) {
        return null;
    }
}
