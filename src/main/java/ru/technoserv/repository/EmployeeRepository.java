package ru.technoserv.repository;

import org.springframework.cache.annotation.Cacheable;
import ru.technoserv.dao.Employee;

public interface EmployeeRepository {

    Employee getEmployeeById(String firstName, String lastName);

    @Cacheable("employees")
    Employee getEmployee(String firstName, String lastName);
}
