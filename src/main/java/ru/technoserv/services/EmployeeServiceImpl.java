package ru.technoserv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
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
    public void createEmployee(Employee employee) {
       // employeeDao.create(new Employee(request.getFirstName(), request.getLastName()));
    }

    @Override
    public void getEmployeeStory(Employee employee) {
        throw new NotImplementedException();
    }

    @Override
    public void transferEmployee(Employee employee) {
        throw new NotImplementedException();
    }

    @Override
    public void removeEmployee(Employee employee) {
        throw new NotImplementedException();
    }

    @Override
    public void changeEmployeeData(Employee employee) {
        throw new NotImplementedException();
    }
}

