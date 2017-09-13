package ru.technoserv.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.technoserv.dao.Department;
import ru.technoserv.dao.DepartmentDao;
import ru.technoserv.dao.EmployeeDao;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger log = Logger.getLogger(DepartmentServiceImpl.class);

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    EmployeeDao employeeDao;
    private boolean isIDLoaded = false;

    @Override
    public Department createDepartment(Department department) {
        departmentDao.create(department);
        return departmentDao.readById(department.getId());
    }

    @Override
    public Department getDepartment(int deptID) {
        Department dep = departmentDao.readById(deptID);
        return dep;
    }

    @Override
    public List<Department> getAllDepartments() {
        log.info("Получаем все отделы");
        List<Department> allEmps = departmentDao.getDepartmentsList();
        return allEmps;
    }

    @Override
    public List<Department> getSubDepts(int deptId) {
        log.info("Получаем подотделы");
        List<Department> subDepts;
        subDepts = departmentDao.getAllSubDepts(deptId);
        return subDepts;
    }

    @Override
    public Department updateDept(Department department) {
        return departmentDao.updateDept(department);
    }

    @Override
    public Department deleteDepartment(int deptID) {
        Department deletedDept = departmentDao.readById(deptID);
        departmentDao.delete(deptID);
        return deletedDept;
    }
}
