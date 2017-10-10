package ru.technoserv.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.domain.DepWithChildren;
import ru.technoserv.domain.Department;
import ru.technoserv.dao.DepartmentDao;
import ru.technoserv.dao.EmployeeDao;
import ru.technoserv.domain.Employee;
import ru.technoserv.exceptions.StuffExceptions;
import ru.technoserv.services.DepartmentService;

import java.util.ArrayList;
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
        logger.info("Создание отдела");
        Integer id = departmentDao.create(department);
        return departmentDao.readById(id);
    }

    @Override
    public Department getDepartment(int deptID) {
        logger.info("Чтение отдела с ID" + deptID);
        Department dep = departmentDao.readById(deptID);
        return dep;
    }

    @Override
    public List<Department> getAllDepartments() {
        logger.info("Чтение всех отделов");
        List<Department> allDeps = departmentDao.getDepartmentsList();
        return allDeps;
    }

    @Override
    public List<Department> getSubDepts(int deptId) {
        logger.info("Чтение подотделов отдела с ID: " + deptId);
        List<Department> subDepts = departmentDao.getAllSubDepts(deptId);
        return subDepts;
    }

    @Transactional
    @Override
    public Department updateDept(Department department) {
        logger.info("Изменение отдела с ID: " + department.getId());
        Department dbDepartment = departmentDao.readById(department.getId());
        Department updatedDepartment = departmentDao.updateDept(department);
        //При назначении сотрудника начальником, сотрудник переводиться в отдел, в котором он является начальником.
        if(!dbDepartment.getDeptHeadId().equals(updatedDepartment.getDeptHeadId())){
            Employee employee = employeeDao.read(department.getDeptHeadId());
            employee.setDepartment(updatedDepartment);
            employeeDao.updateEmployee(employee);
        }
        return updatedDepartment;
    }

    @Override
    public Department deleteDepartment(int deptID) {
        logger.info("Удаление отдела с ID: " + deptID);
        if (!departmentDao.getAllSubDepts(deptID).isEmpty()) {
            logger.info("Удаление отдела невозможно: у отдела есть дочерние отделы!");
            throw new RuntimeException(StuffExceptions.SUBDEPTS_ERROR.toString());
        }

        if(!employeeDao.getAllFromDept(deptID).isEmpty()) {
            logger.info("Удаление отдела невозможно: в отделе есть сотрудники!");
            throw new RuntimeException(StuffExceptions.EMPLOYEES_IN_DEPARTMENT.toString());
        }

        Department deletedDept = departmentDao.readById(deptID);
        departmentDao.delete(deptID);

        return deletedDept;
    }

    @Override
    public List<DepWithChildren> getHierarchy() {
        List<DepWithChildren> hierarchy = departmentDao.getHierarchy();

        return hierarchy;
    }
}
