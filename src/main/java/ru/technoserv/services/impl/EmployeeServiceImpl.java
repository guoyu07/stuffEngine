package ru.technoserv.services.impl;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.dao.*;
import ru.technoserv.domain.Department;
import ru.technoserv.domain.Employee;
import ru.technoserv.domain.EmployeeHistory;
import ru.technoserv.exceptions.EmployeeTheHeadOfDepartment;
import ru.technoserv.services.EmployeeService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;


/**
 * Управление информацией о сотрудниках
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("Создание сотрудника");

        EmployeeHistory eh = new EmployeeHistory(employee);
        eh.setDepartment(departmentDao.readById(employee.getDepartment().getId()));
        Integer id = employeeDao.create(eh);
        Employee createdEmployee = new Employee(employeeDao.read(id));

        return createdEmployee;
    }

    @Override
    public List<EmployeeHistory> getEmployeeStory(int id) {
        List<EmployeeHistory> employeeHistoryList = employeeDao.getEmployeeStory(id);

        return employeeHistoryList;
    }

    @Override
    public void removeEmployee(int id) {
        logger.info("Удаление сотрудника с ID: " + id);
        Department empDept = employeeDao.read(id).getDepartment();
        int empDeptHeadID = empDept.getDeptHeadId();

        if (empDeptHeadID == id) {
            logger.info("Удаление сотрудника невозможно: сотрудник является начальником отдела!");
            throw new EmployeeTheHeadOfDepartment(id);
        }
        employeeDao.delete(id);
    }

    @Override
    public Employee changeEmployee(Employee employee) {
        logger.info("Изменение сотрудника с ID: " + employee.getEmpID());
        Employee dbEmployee = new Employee(employeeDao.read(employee.getEmpID()));
        employee.setDepartment(departmentDao.readById(employee.getDepartment().getId()));
        if(!employee.getDepartment().equals(dbEmployee.getDepartment())){
            if(dbEmployee.getEmpID()
                    .equals(dbEmployee.getDepartment()
                            .getDeptHeadId())) {
                logger.info("Изменение сотрудника невозможно: начальник отдела не может быть переведен в другой!");
                throw new EmployeeTheHeadOfDepartment(employee.getEmpID());
            }
        }

        EmployeeHistory eh = new EmployeeHistory(employee);
        eh.setDepartment(departmentDao.readById(employee.getDepartment().getId()));

        EmployeeHistory updatedEmpH = employeeDao.updateEmployee(eh);
        Employee updatedEmployee = new Employee(updatedEmpH);

        return updatedEmployee;
    }

    public List<Employee> getEmployees(int depID){
        logger.info("Получение сотрудников отдела с ID: " + depID);
        List<EmployeeHistory> allEmpsHistory = employeeDao.getAllFromDept(depID);
        for(EmployeeHistory eh : allEmpsHistory) {
            eh.setDepartment(departmentDao.readById(depID));
        }
        List<Employee> allEmps = buildEmpsList(allEmpsHistory);

        return allEmps;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return buildEmpsList(employeeDao.getAllEmployees());
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        logger.info("Получение сотрудника с ID:" + id);
        EmployeeHistory eh = employeeDao.read(id);
        Employee employee = new Employee(eh);

        return employee;
    }

    private List<Employee> buildEmpsList(List<EmployeeHistory> empHList) {
        List<Employee> emps = new ArrayList<>();

        for(EmployeeHistory eh : empHList) {
            emps.add(new Employee(eh));
        }

        return emps;
    }
}

