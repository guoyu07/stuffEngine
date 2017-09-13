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

    private static final Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    EmployeeDao employeeDao;
    private boolean isIDLoaded = false;

    @Override
    public Department createDepartment(Department department) {
        logger.info("Создаем отдел");
        departmentDao.create(department);
        return departmentDao.readById(department.getId());
    }

    @Override
    public Department getDepartment(int deptID) {
        logger.info("Получаем отдел по ид");
        Department dep = departmentDao.readById(deptID);
        return dep;
    }

    @Override
    public List<Department> getAllDepartments() {
        logger.info("Получаем все отделы");
        List<Department> allEmps = departmentDao.getDepartmentsList();
        return allEmps;
    }

    @Override
    public List<Department> getSubDepts(int deptId) {
        logger.info("Получаем подотделы");
        List<Department> subDepts;
        subDepts = departmentDao.getAllSubDepts(deptId);
        return subDepts;
    }

    @Override
    public Department updateDept(Department department) {
        logger.info("Меняем параметры отдела");
        return departmentDao.updateDept(department);
    }

    @Override
    public Department deleteDepartment(int deptID) {
        logger.info("Удаляем отдел");
        Department deletedDept = departmentDao.readById(deptID);
        departmentDao.delete(deptID);
        return deletedDept;
    }
}
