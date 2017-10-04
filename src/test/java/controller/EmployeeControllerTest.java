package controller;


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