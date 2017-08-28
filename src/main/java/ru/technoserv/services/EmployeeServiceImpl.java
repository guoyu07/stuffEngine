package ru.technoserv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.technoserv.dao.Employee;
import ru.technoserv.dao.EmployeeDao;


@Component
@ComponentScan("ru")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    public Employee getEmployee(String firstName, String lastName){
        return employeeDao.read(firstName, lastName);
    }

    static int id=10;

    public void addEmployee(String firstName, String lastName){
        employeeDao.create(new Employee(id++, firstName, lastName ));
    }
}

