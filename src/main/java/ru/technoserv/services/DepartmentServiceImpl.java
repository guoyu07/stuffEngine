package ru.technoserv.services;
/*
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.technoserv.dao.Department;
import ru.technoserv.dao.DepartmentDao;
import ru.technoserv.dao.EmployeeDao;

import java.util.List;

@Component
@ComponentScan("ru")
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger log = Logger.getLogger(DepartmentServiceImpl.class);

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    EmployeeDao employeeDao;
    private boolean isIDLoaded = false;

    @Override
    public Department createDepartment(Department department) {
        if (!isIDLoaded) {
            Department.setGlobalID(departmentDao.getID());
            isIDLoaded = true;
        }
        department.setId(Department.getGlobalID());
        departmentDao.create(department);
        return department;
    }

    @Override
    public Department getDepartment(int deptID) {
        return departmentDao.readById(deptID);
    }

    @Override
    public List<Department> getAllDepartments() {
        log.info("Получаем все отделы");
        List<Department> allEmps;
        allEmps = departmentDao.getDepartmentsList();
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
    public void reassignDepartment(int deptID, Integer newParentDeptID) {
        departmentDao.updateParentDeptId(newParentDeptID, deptID);
    }

    @Override
    public Department deleteDepartment(int deptID) {
        Department deletedDept = departmentDao.readById(deptID);
        departmentDao.delete(deptID);
        return deletedDept;
    }
}
*/