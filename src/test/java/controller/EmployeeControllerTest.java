package controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.technoserv.controller.EmployeeController;
import ru.technoserv.domain.Employee;
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

        @Bean
        public BindingResult bindingResult(){
            return mock(BindingResult.class);
        }

    }

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BindingResult binding;

    private MockMvc mockMvc;

    private Employee emp;

    @Before
    public void setup() throws Exception {
        emp = new Employee();
        emp.setEmpID(1);
        emp.setLastName("Ivanov");
        emp.setFirstName("Ivan");
       /* emp.setDepartment("Помещения");
        emp.setGender('М');
        emp.setGrade("E");*/
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
                .andExpect(jsonPath("$[0].empID",is(1)))
                .andExpect(jsonPath("$[0].lastName",is("Ivanov")))
                .andExpect(jsonPath("$[1].empID",is(2)))
                .andExpect(jsonPath("$[1].lastName",is("Petrov")));
    }

    @Test
    public void testGetEmployee() throws Exception {
        when(employeeService.getEmployee(1)).thenReturn(emp);
        mockMvc.perform(get("/employee/1")).andExpect(status().isOk())
                .andExpect(jsonPath("empID", is(1)))
                .andExpect(jsonPath("lastName", is("Ivanov")));
    }

   /* @Test
    public void testCreateEmployee() throws  Exception{
        @SuppressWarnings("unchecked") ResponseEntity responseEntity = new ResponseEntity(emp, HttpStatus.CREATED);
        when(employeeService.createEmployee(emp)).thenReturn(emp);
        when(binding.hasErrors()).thenReturn(false);
        Assert.assertEquals(responseEntity, employeeController.createEmployee(emp));
    }*/
    /*
    @Test
    public void testWrongParamCreateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setLastName("Ivanov");
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        mockMvc.perform(post("/employee/").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isBadRequest());
    }        */

    @Test
    public void  testEmployeeRemove() throws Exception {
        doNothing().when(employeeService).removeEmployee(1);
        mockMvc.perform(delete("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("delete"));

    }
}
