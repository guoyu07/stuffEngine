package ru.technoserv.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.technoserv.controller.Employee;
import ru.technoserv.services.EmployeeService;

@Component
@ComponentScan("ru")
public class EmployeeRepositoryImpl implements EmployeeRepository{

    @Autowired
    EmployeeService employeeService;

    @Override
    @Cacheable("employees")
    public Employee getEmployeeById(int id){
        Employee e =employeeService.getEmployee(id);
        simulateSlowService();
        return e;
    }

    private void simulateSlowService() {
        try {
            long time = (long) (5000L);
            System.out.println("Sleep");
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }


}
