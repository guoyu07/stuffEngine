package ru.technoserv.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.technoserv.dao.*;
import ru.technoserv.domain.Department;
import ru.technoserv.domain.Employee;
import ru.technoserv.domain.EmployeeHistory;
import ru.technoserv.exceptions.MyRuntimeException;
import ru.technoserv.exceptions.StuffExceptions;
import ru.technoserv.services.EmployeeService;

import java.util.List;


/**
 * Управление информацией о сотрудниках
 */
@Service
public class EmployeeServiceImpl extends SpringBeanAutowiringSupport implements EmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    private final CertificateDao certificateDao;

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(CertificateDao certificateDao, EmployeeDao employeeDao) {
        this.certificateDao = certificateDao;
        this.employeeDao = employeeDao;
    }


    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("Создание сотрудника");
        Integer id = employeeDao.create(employee);
        return employeeDao.read(id);
    }

    @Override
    public List<EmployeeHistory> getEmployeeStory(int id) {
        return employeeDao.getEmployeeStory(id);
    }

    @Override
    public void removeEmployee(int id) {
        logger.info("Удаление сотрудника с ID: " + id);
        Department empDept = employeeDao.read(id).getDepartment();
        if(empDept.getDeptHeadId()!=null) {
            int empDeptHeadID = empDept.getDeptHeadId();
            if (empDeptHeadID == id) {
                logger.info("Удаление сотрудника невозможно: сотрудник является начальником отдела!");
                throw new MyRuntimeException(StuffExceptions.EMPLOYEE_THE_HEAD_ERROR);
            }
        }
        employeeDao.delete(id);
        certificateDao.deleteAllCertsByEmpID(id);

    }

    @Override
    public Employee changeEmployee(Employee employee) {
        logger.info("Изменение сотрудника с ID: " + employee.getEmpID());
        Employee dbEmployee = employeeDao.read(employee.getEmpID());
        if(!(employee.getDepartment().getId().equals(dbEmployee.getDepartment().getId()))
                && dbEmployee.getEmpID()
                .equals(dbEmployee.getDepartment()
                        .getDeptHeadId())){
                logger.info("Изменение сотрудника невозможно: начальник отдела не может быть переведен в другой!");
                throw new MyRuntimeException(StuffExceptions.EMPLOYEE_THE_HEAD_ERROR);
        }
        return employeeDao.updateEmployee(employee);
    }

    public List<Employee> getEmployees(int depID){
        logger.info("Чтение сотрудников отдела с ID: " + depID);
        return employeeDao.getAllFromDept(depID);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        logger.info("Чтение сотрудника с ID:" + id);
        return employeeDao.read(id);
    }

    @Override
    public List<Employee> getPartOfEmployeeList(int start, int num) {
        List<Employee> employees = getAllEmployees();
        if(start>=employees.size()) throw new MyRuntimeException(StuffExceptions.NOT_FOUND);
        if(start+num>=employees.size()) {
            employees = employees.subList(start, employees.size());
        }else{
            employees = employees.subList(start, start+num);
        }
        return employees;
    }

}

