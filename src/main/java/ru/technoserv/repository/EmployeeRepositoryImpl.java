package ru.technoserv.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.technoserv.dao.Employee;
import ru.technoserv.services.EmployeeService;

/**
 * Проба использования кэша для полученного сотрудника
 */
@Component
@ComponentScan("ru")
public class EmployeeRepositoryImpl implements EmployeeRepository{

    @Autowired
    EmployeeService employeeService;

    @Override
    @Cacheable("employees")
    public Employee getEmployee(String firstName, String lastName){
        Employee e = employeeService.getEmployee(firstName, lastName);
        simulateSlowService();
        return e;
    }

    private void simulateSlowService() {
        try {
            long time = 5000L;
            System.out.println("Sleep");
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
