package ru.technoserv.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.technoserv.dao.CertificateDao;
import ru.technoserv.dao.EmployeeDao;
import ru.technoserv.domain.Employee;
import ru.technoserv.domain.Grade;
import ru.technoserv.domain.Position;
import ru.technoserv.services.EmployeeService;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static ru.technoserv.util.TestUtils.getNewDep;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    EmployeeDao employeeDao;

    @Mock
    CertificateDao certificateDao;

    @InjectMocks
    EmployeeService employeeService = new EmployeeServiceImpl();


    private Employee employee;
    private Employee created;

    @Before
    public void setUp() throws Exception {
        employee = Employee.newBuilder()
                .empId(null).position(new Position(1)).grade(new Grade(1))
                .lastName("Миров").firstName("Евгений").patrName("Артурович")
                .department(getNewDep(1, null, "Головной", 1))
                .gender("М").birthday(Date.valueOf(LocalDate.of(1983, 2, 23)))
                .salary(new BigDecimal(1000.00)).build();
        created = Employee.newBuilder()
                .empId(1).position(new Position(1, "Начальник отдела")).grade(new Grade(1, "A"))
                .lastName("Миров").firstName("Евгений").patrName("Артурович")
                .department(getNewDep(1, null, "Головной", 1))
                .gender("М").birthday(Date.valueOf(LocalDate.of(1983, 2, 23)))
                .salary(new BigDecimal(1000.00)).build();
    }

    @Test
    public void createEmployee() throws Exception {
        Employee expected = Employee.newBuilder()
                .empId(1).position(new Position(1, "Начальник отдела")).grade(new Grade(1, "A"))
                .lastName("Миров").firstName("Евгений").patrName("Артурович")
                .department(getNewDep(1, null, "Головной", 1))
                .gender("М").birthday(Date.valueOf(LocalDate.of(1983, 2, 23)))
                .salary(new BigDecimal(1000.00)).build();

        when(employeeDao.create(any(Employee.class))).thenReturn(1);
        when(employeeDao.read(1)).thenReturn(created);

        Employee createdEmployee = employeeService.createEmployee(employee);
        assertEquals(expected, createdEmployee);
    }

    @Test
    public void getEmployeeStory() throws Exception {
    }

    @Test
    public void removeEmployee() throws Exception {
    }

    @Test
    public void changeEmployee() throws Exception {
    }

    @Test
    public void getEmployees() throws Exception {
    }

    @Test
    public void getAllEmployees() throws Exception {
    }

    @Test
    public void getEmployee() throws Exception {
    }

}