package ru.technoserv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.technoserv.dao.Department;
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
       employeeDao.create(employee);
    }

    @Override
    public void getEmployeeStory(Employee employee) {
        throw new NotImplementedException();
    }

    @Override
    public void transferEmployee(Employee employee) {
        employeeDao.updateDept(employee.getEmpID(), employee.getDepartment());
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
        employeeDao.updateGrade(employee.getEmpID(), employee.getGrade());
    }

    public void changeEmployeePosition(Employee employee) {
        employeeDao.updatePsoition(employee.getEmpID(), employee.getPosition());
    }

    public void getEmployees(Department department){
        employeeDao.getAllFromDept(department.getID());

    }
}

