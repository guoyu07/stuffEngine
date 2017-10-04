package controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.technoserv.controller.EmployeeController;
import ru.technoserv.dao.DepartmentDao;
import ru.technoserv.dao.EmployeeDao;
import ru.technoserv.dao.impl.HibernateDepartmentDao;
import ru.technoserv.dao.impl.HibernateEmployeeDao;
import ru.technoserv.domain.Department;
import ru.technoserv.domain.Employee;
import ru.technoserv.domain.EmployeeHistory;
import ru.technoserv.services.EmployeeService;
import ru.technoserv.services.impl.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
    @Mock
    EmployeeDao employeeDao;

    @Mock
    DepartmentDao departmentDao;

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void changeEmployeeTheHead(){

        Employee sendEmployee = new Employee();
        Department sendDepartment = new Department();
        sendDepartment.setId(1);
        sendEmployee.setEmpID(1);
        sendEmployee.setDepartment(sendDepartment);


        EmployeeHistory dbEmployee = new EmployeeHistory();
        dbEmployee.setEmpID(1);
        Department dbDepartment = new Department();
        dbDepartment.setDeptHeadId(1);
        dbDepartment.setId(1);
        dbEmployee.setDepartment(dbDepartment);

        EmployeeHistory eh = new EmployeeHistory(sendEmployee);

        when(employeeDao.read(anyInt())).thenReturn(dbEmployee);
        when(departmentDao.readById(anyInt())).thenReturn(dbDepartment);
        when(employeeDao.updateEmployee(anyObject())).thenReturn(eh);
        employeeService.changeEmployee(sendEmployee);

    }

}
*/