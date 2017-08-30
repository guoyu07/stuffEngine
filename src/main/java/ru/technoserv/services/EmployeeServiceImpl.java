package ru.technoserv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.technoserv.controller.JSON.Request.EmployeeRequest;
import ru.technoserv.dao.Employee;
import ru.technoserv.dao.EmployeeDao;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Управление информацией о сотрудниках
 */
@Component
@ComponentScan("ru")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public void createEmployee(EmployeeRequest request) {
        employeeDao.create(new Employee(request.getFirstName(), request.getLastName()));
    }

    @Override
    public void getEmployeeStory(EmployeeRequest request) {
        throw new NotImplementedException();
    }

    @Override
    public void transferEmployee(EmployeeRequest request) {
        throw new NotImplementedException();
    }

    @Override
    public void removeEmployee(EmployeeRequest request) {
        throw new NotImplementedException();
    }

    @Override
    public void changeEmployeeData(EmployeeRequest request) {
        throw new NotImplementedException();
    }
}

