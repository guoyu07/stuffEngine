package ru.technoserv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.technoserv.exceptions.InvalidInputException;
import ru.technoserv.dao.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Управление информацией о сотрудниках
 */
@Component
@ComponentScan("ru")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    private boolean isIdLoaded = false;

    @Override
    public Employee createEmployee(Employee employee) {
        if(!isIdLoaded){
            Employee.setGlobalID(employeeDao.getID());
            isIdLoaded = true;
        }
        employee.setEmpID(Employee.getGlobalID());
        System.out.println(employee);
        employeeDao.create(employee);
        return employee;
    }

    @Override
    public void getEmployeeStory(Employee employee) {
        throw new NotImplementedException();
    }

    @Override
    public void transferEmployee(int empID, int depID){
        //TODO проверка на начальника и его затирание
        employeeDao.updateDept(empID, depID);
    }

    @Override
    public void removeEmployee(int id) {
        employeeDao.delete(id);
    }

    @Override
    public void changeEmployeeSalary(Employee employee) {
        employeeDao.updateSalary(employee.getEmpID(), employee.getSalary());
    }

    public void changeEmployeeGrade(Employee employee) {
        throw new NotImplementedException();
    }

    public void changeEmployeePosition(Employee employee) {
        throw new NotImplementedException();
    }

    public List<Employee> getEmployees(int depID){
        return employeeDao.getAllFromDept(depID);
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeDao.read(id);
    }
}

