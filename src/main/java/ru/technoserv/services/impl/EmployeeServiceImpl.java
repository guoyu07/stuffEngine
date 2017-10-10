package ru.technoserv.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.technoserv.dao.*;
import ru.technoserv.domain.Certificate;
import ru.technoserv.domain.Department;
import ru.technoserv.domain.Employee;
import ru.technoserv.domain.EmployeeHistory;
import ru.technoserv.exceptions.StuffExceptions;
import ru.technoserv.services.EmployeeService;

import java.util.List;


/**
 * Управление информацией о сотрудниках
 */
@Service
public class EmployeeServiceImpl extends SpringBeanAutowiringSupport implements EmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private CertificateDao certificateDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("Создание сотрудника");
        Integer id = employeeDao.create(employee);
        Employee createdEmployee = employeeDao.read(id);
        return createdEmployee;
    }

    @Override
    public List<EmployeeHistory> getEmployeeStory(int id) {
        List<EmployeeHistory> employeeHistoryList = employeeDao.getEmployeeStory(id);
        employeeHistoryList.sort(EmployeeHistory.HistoryComparator);

        return employeeHistoryList;
    }

    @Override
    public void removeEmployee(int id) {
        logger.info("Удаление сотрудника с ID: " + id);
        Department empDept = employeeDao.read(id).getDepartment();
        if(empDept.getDeptHeadId()!=null) {
            int empDeptHeadID = empDept.getDeptHeadId();
            if (empDeptHeadID == id) {
                logger.info("Удаление сотрудника невозможно: сотрудник является начальником отдела!");
                throw new RuntimeException(StuffExceptions.EMPLOYEE_THE_HEAD_ERROR.toString());
            }
        }
        employeeDao.delete(id);
        certificateDao.deleteAllCertsByEmpID(id);

    }

    @Override
    public Employee changeEmployee(Employee employee) {
        logger.info("Изменение сотрудника с ID: " + employee.getEmpID());
        Employee dbEmployee = employeeDao.read(employee.getEmpID());
        if(!(employee.getDepartment().getId().equals(dbEmployee.getDepartment().getId()))){
            if(dbEmployee.getEmpID()
                    .equals(dbEmployee.getDepartment()
                            .getDeptHeadId())) {
                logger.info("Изменение сотрудника невозможно: начальник отдела не может быть переведен в другой!");
                throw new RuntimeException(StuffExceptions.EMPLOYEE_THE_HEAD_ERROR.toString());
            }
        }
        Employee updatedEmpH = employeeDao.updateEmployee(employee);
        return updatedEmpH;
    }

    public List<Employee> getEmployees(int depID){
        logger.info("Чтение сотрудников отдела с ID: " + depID);
        List<Employee> allEmps = employeeDao.getAllFromDept(depID);
        return allEmps;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        logger.info("Чтение сотрудника с ID:" + id);
        Employee e = employeeDao.read(id);
        return e;
    }

    @Override
    public List<Employee> getPartOfEmployeeList(int start, int num) {
        List<Employee> employees = getAllEmployees();
        if(start>=employees.size()) throw new RuntimeException(StuffExceptions.NOT_FOUND.toString());
        if(start+num>=employees.size()) {
            employees = employees.subList(start, employees.size()-1);
        }else{
            employees = employees.subList(start, start+num);
        }
        return employees;
    }

}

