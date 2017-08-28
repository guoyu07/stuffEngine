package ru.technoserv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.technoserv.dao.Employee;
import ru.technoserv.dao.EmployeeDao;

/**
 * Управление информацией о сотрудниках
 */
@Component
@ComponentScan("ru")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * Получение объекта сотрудника по его имени и фамилии
     * @param firstName имя сотрудника
     * @param lastName фамилия сотрудника
     * @return сотрудника с заданными именем и фамилией
     */
    public Employee getEmployee(String firstName, String lastName){
        return employeeDao.read(firstName, lastName);
    }

    /**
     * Добавление сотрудника в базу
     * @param firstName имя сотрудника
     * @param lastName фамилия сотрудника
     */
    public void addEmployee(String firstName, String lastName){
        employeeDao.create(new Employee(firstName, lastName ));
    }
}

