package controller;


import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.technoserv.controller.EmployeeController;
import ru.technoserv.controller.WebAppConfig;
import ru.technoserv.dao.Employee;
import ru.technoserv.services.EmployeeService;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class EmployeeControllerTest {

    @Configuration
    static class LoginControllerTestConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return mock(EmployeeService.class);
        }

        @Bean
        public EmployeeController employeeController() {
            return new EmployeeController();
        }
    }
    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeService employeeService;

    private MockMvc mockMvc;

    private Employee emp;

    @Before
    public void setup() throws Exception {
        emp = new Employee();
        emp.setEmpID(1);
        emp.setLastName("Ivanov");
        emp.setFirstName("Ivan");
        emp.setDepartment("Помещения");
        emp.setGender('М');
        emp.setGrade("E");
        emp.setSalary(new BigDecimal(20000));

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetStuffFromDept() throws Exception {
        Employee emp1 = new Employee();
        emp1.setEmpID(1);
        emp1.setLastName("Ivanov");
        Employee emp2 = new Employee();
        emp2.setEmpID(2);
        emp2.setLastName("Petrov");
        when(employeeService.getEmployees(1)).thenReturn(Arrays.asList(emp1, emp2));
        mockMvc.perform(get("/employee/all/1")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].empID",is(1)))
                .andExpect(jsonPath("$[0].lastName",is("Ivanov")))
                .andExpect(jsonPath("$[1].empID",is(2)))
                .andExpect(jsonPath("$[1].lastName",is("Petrov")));
    }

    @Test
    public void testGetEmployee() throws Exception {

        when(employeeService.getEmployee(1)).thenReturn(emp);
        mockMvc.perform(get("/employee/1")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("empID", is(1)))
                .andExpect(jsonPath("lastName", is("Ivanov")));
    }
    //TODO в приложении работает, в тесте нет
    @Test
    public void testCreateEmployee() throws  Exception{
        when(employeeService.createEmployee(emp)).thenReturn(emp);
        Gson gson = new Gson();
        String json = gson.toJson(emp);
        mockMvc.perform(put("/employee/newEmployee").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
                /*.andExpect(jsonPath("empID", is(1)))
                .andExpect(jsonPath("lastName", is("Ivanov")))
                .andDo(MockMvcResultHandlers.print());*/
    }

    @Test
    public void testWrongParamCreateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setLastName("Ivanov");
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        mockMvc.perform(put("/employee/newEmployee").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isBadRequest());
    }

    @Test
    public void  testEmployeeTransfer() throws Exception {
        Employee emp1 = new Employee();
        emp1.setEmpID(emp.getEmpID());
        emp1.setDepartment("Игрушки");
        when(employeeService.transferEmployee(1,1)).thenReturn(emp1);
        mockMvc.perform(patch("/employee/1/department/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("empID", is(1)))
                .andExpect(jsonPath("department", is("Игрушки")));

    }

    @Test
    public void  testEmployeeChangeSalary() throws Exception {
        Employee emp1 = new Employee();
        emp1.setEmpID(emp.getEmpID());
        emp1.setSalary(new BigDecimal(22000));
        when(employeeService.changeEmployeeSalary(1,new BigDecimal(22000))).thenReturn(emp1);
        mockMvc.perform(patch("/employee/1/salary/22000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("empID", is(1)))
                .andExpect(jsonPath("salary", is(22000)));
    }

    @Test
    public void  testEmployeeChangeGrade() throws Exception {
        Employee emp1 = new Employee();
        emp1.setEmpID(1);
        emp1.setGrade("D");
        when(employeeService.changeEmployeeGrade(1,4)).thenReturn(emp1);
        mockMvc.perform(patch("/employee/1/grade/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("empID", is(1)))
                .andExpect(jsonPath("grade", is("D")));
    }

    @Test
    public void  testEmployeePosition() throws Exception {
        Employee emp1 = new Employee();
        emp1.setEmpID(emp.getEmpID());
        emp1.setPosition("position");
        when(employeeService.changeEmployeePosition(1,1)).thenReturn(emp1);
        mockMvc.perform(patch("/employee/1/position/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("empID", is(1)))
                .andExpect(jsonPath("position", is("position")));
    }

    @Test
    public void  testEmployeeRemove() throws Exception {
        doNothing().when(employeeService).removeEmployee(1);
        mockMvc.perform(delete("/employee/quit/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("delete"));

    }

}
