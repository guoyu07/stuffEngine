package controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.technoserv.dao.DepartmentDao;
import ru.technoserv.dao.EmployeeDao;
import ru.technoserv.domain.Department;
import ru.technoserv.domain.Employee;
import ru.technoserv.services.EmployeeService;
import ru.technoserv.services.impl.EmployeeServiceImpl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
    @Mock
    EmployeeDao employeeDao;

    @Mock
    DepartmentDao departmentDao;

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void changeEmployee(){

        Employee sendEmployee = new Employee();
        Department sendDepartment = new Department();
        sendDepartment.setId(1);
        sendEmployee.setEmpID(1);
        sendEmployee.setDepartment(sendDepartment);


        Employee dbEmployee = new Employee();
        dbEmployee.setEmpID(1);
        Department dbDepartment = new Department();
        dbDepartment.setDeptHeadId(1);
        dbDepartment.setId(1);
        dbEmployee.setDepartment(dbDepartment);

        when(employeeDao.read(anyInt())).thenReturn(dbEmployee);
        when(departmentDao.readById(anyInt())).thenReturn(dbDepartment);
        when(employeeDao.updateEmployee(anyObject())).thenReturn(sendEmployee);
        employeeService.changeEmployee(sendEmployee);

    }

    @Test(expected = RuntimeException.class)
    public void changeEmployeeTheHeadOfDepartment(){

        Employee sendEmployee = new Employee();
        Department sendDepartment = new Department();
        sendDepartment.setId(2);
        sendEmployee.setEmpID(1);
        sendEmployee.setDepartment(sendDepartment);


        Employee dbEmployee = new Employee();
        dbEmployee.setEmpID(1);
        Department dbDepartment = new Department();
        dbDepartment.setDeptHeadId(1);
        dbDepartment.setId(1);
        dbEmployee.setDepartment(dbDepartment);


        when(employeeDao.read(anyInt())).thenReturn(dbEmployee);
        when(departmentDao.readById(anyInt())).thenReturn(dbDepartment);
        when(employeeDao.updateEmployee(anyObject())).thenReturn(sendEmployee);
        employeeService.changeEmployee(sendEmployee);

    }

}
