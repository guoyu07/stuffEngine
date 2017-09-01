package ru.technoserv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.technoserv.Exceptions.InvalidInputException;
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
    public void createEmployee(Employee employee) {
        if(!isIdLoaded){
            Employee.setGlobalID(employeeDao.getID());
            isIdLoaded = true;
        }
        employee.setEmpID(Employee.getGlobalID());
        System.out.println(employee);
        employeeDao.create(employee);
    }

    @Override
    public void getEmployeeStory(Employee employee) {
        throw new NotImplementedException();
    }

    @Override
    public void transferEmployee(Employee employee){
        Employee emp = employeeDao.read(employee.getEmpID());
        Department dep = departmentDao.readByName(emp.getDepartment());
        if(dep.getDeptHeadId().equals(emp.getEmpID())){
            departmentDao.updateDeptHead(null, dep.getId());
        }
        employeeDao.updateDept(employee.getEmpID(), employee.getDepartment());
    }

    @Override
    public void removeEmployee(int id) {
        Employee emp = employeeDao.read(id);
        Department dep = departmentDao.readByName(emp.getDepartment());
        if(dep.getDeptHeadId().equals(emp.getEmpID())){
            departmentDao.updateDeptHead(null, dep.getId());
        }
        employeeDao.delete(id);
    }

    @Override
    public void changeEmployeeSalary(Employee employee) {
        employeeDao.updateSalary(employee.getEmpID(), employee.getSalary());
    }

    public void changeEmployeeGrade(Employee employee) {
        employeeDao.updateGrade(employee.getEmpID(), employee.getGrade());
    }

    public void changeEmployeePosition(Employee employee) {
            employeeDao.updatePosition(employee.getEmpID(), employee.getPosition());
    }

    public List<Employee> getEmployees(String department){
        List<Employee> employees;
        employees = employeeDao.getAllFromDept(department);
        return employeeDao.getAllFromDept(department);
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeDao.read(id);
    }
}

