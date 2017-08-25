package ru.technoserv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.technoserv.dao.Employee;
import ru.technoserv.dao.EmployeeDao;
import ru.technoserv.dao.OracleEmployeeDao;


@Component
@ComponentScan("ru")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(){

    }

    public Employee getEmployee(int id){
        return employeeDao.read(id);
    }

    static int id=10;

    public void addEmployee(String firstName, String lastName){
        System.out.println(firstName + " "+lastName);
        employeeDao.create(new Employee(id++, firstName, lastName ));
    }
}

