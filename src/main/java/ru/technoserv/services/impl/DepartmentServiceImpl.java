package ru.technoserv.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.domain.Department;
import ru.technoserv.dao.DepartmentDao;
import ru.technoserv.dao.EmployeeDao;
import ru.technoserv.domain.Employee;
import ru.technoserv.exceptions.MyRuntimeException;
import ru.technoserv.exceptions.StuffExceptions;
import ru.technoserv.services.DepartmentService;

import java.util.List;

/**
 * Управление информацией об отделах
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = Logger.getLogger(DepartmentServiceImpl.class);


    DepartmentDao departmentDao;

    EmployeeDao employeeDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao, EmployeeDao employeeDao){
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
    }


    @Override
    public Department createDepartment(Department department) {
        logger.info("Создание отдела");
        Integer id = departmentDao.create(department);
        return departmentDao.readById(id);
    }

    @Override
    public Department getDepartment(int deptID) {
        logger.info("Чтение отдела с ID" + deptID);
        return departmentDao.readById(deptID);
    }

    @Override
    public List<Department> getAllDepartments() {
        logger.info("Чтение всех отделов");
        return departmentDao.getDepartmentsList();
    }

    @Override
    public List<Department> getSubDepts(int deptId) {
        logger.info("Чтение подотделов отдела с ID: " + deptId);
        return departmentDao.getAllSubDepts(deptId);
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
            throw new MyRuntimeException(StuffExceptions.SUBDEPTS_ERROR);
        }

        if(!employeeDao.getAllFromDept(deptID).isEmpty()) {
            logger.info("Удаление отдела невозможно: в отделе есть сотрудники!");
            throw new MyRuntimeException(StuffExceptions.EMPLOYEES_IN_DEPARTMENT);
        }

        Department deletedDept = departmentDao.readById(deptID);
        departmentDao.delete(deptID);

        return deletedDept;
    }
}
