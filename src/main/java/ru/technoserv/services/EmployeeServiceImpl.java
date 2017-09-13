package ru.technoserv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.dao.*;
import ru.technoserv.exceptions.EmployeeNotFoundException;
import ru.technoserv.exceptions.EmployeeTheHeadOfDepartment;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Управление информацией о сотрудниках
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

   // private static final Logger log = Logger.getLogger(OracleEmployeeDao.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Employee createEmployee(Employee employee) {
     //   log.info("Посылаем запрос dao на создание сотрудника: "+employee);
        employeeDao.create(employee);
        return employeeDao.read(employee.getEmpID());
    }

    @Override
    public void getEmployeeStory(Employee employee) {
        throw new NotImplementedException();
    }

    @Override
    public void removeEmployee(int id) {
        employeeDao.delete(id);
    }

    @Override
    public Employee changeEmployee(Employee employee) {
        Employee dbEmployee = employeeDao.read(employee.getEmpID());
        if(!employee.getDepartment().equals(dbEmployee.getDepartment())){
            if(employee.getEmpID().equals(dbEmployee.getDepartment().getDeptHeadId())) throw new EmployeeTheHeadOfDepartment(employee.getEmpID());
        }
        return employeeDao.updateEmployee(employee);
    }

    public List<Employee> getEmployees(int depID){
      //  log.info("Посылаем запрос dao на получение сотрудников отдела с ИД "+depID);
        return employeeDao.getAllFromDept(depID);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDao.read(id);
    }
}

