package controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.technoserv.dao.EmployeeDao;
import ru.technoserv.domain.Employee;
import ru.technoserv.services.EmployeeService;
import ru.technoserv.services.impl.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService service = new EmployeeServiceImpl();

    private List<Employee> employees;

    @Before
    public void init(){
        Employee employee = new Employee();
        employee.setEmpID(1);
        Employee employee2 = new Employee();
        employee2.setEmpID(2);
        employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee2);
    }

    @Test
    public void getPartEmployeeTest(){
        when(employeeDao.getAllEmployees()).thenReturn(employees);
        List<Employee> res = service.getPartOfEmployeeList(0,2);
        Assert.assertNotNull(res);
    }

    @Test
    public void getPartEmployeeReturnSizeTest(){
        when(employeeDao.getAllEmployees()).thenReturn(employees);
        List<Employee> res = service.getPartOfEmployeeList(0, 10);
        Assert.assertEquals(res.size(), employees.size());
    }

    @Test(expected = RuntimeException.class)
    public void getPartExceptionTest(){
        when(employeeDao.getAllEmployees()).thenReturn(employees);
        List<Employee> res = service.getPartOfEmployeeList(employees.size()+1, 10);
    }

}
