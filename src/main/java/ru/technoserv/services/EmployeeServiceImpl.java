package ru.technoserv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.technoserv.dao.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
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
    private boolean isIdLoaded = false;

    @Override
    public Employee createEmployee(Employee employee) {
//        if(!isIdLoaded) {
//            Employee.setGlobalID(employeeDao.getID());
//            isIdLoaded = true;
//        }
//        employee.setEmpID(Employee.getGlobalID());
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

