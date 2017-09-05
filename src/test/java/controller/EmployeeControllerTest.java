package controller;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        Employee emp1 = new Employee();
        emp1.setEmpID(1);
        emp1.setLastName("Ivanov");
        Employee emp2 = new Employee();
        emp2.setEmpID(2);
        emp2.setLastName("Petrov");
        when(this.employeeService.getEmployees(1)).thenReturn(Arrays.asList(emp1, emp2));
    }

    @Test
    public void test() throws Exception {

        mockMvc.perform(get("/employee/all/1")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].empID",is(1)))
                .andExpect(jsonPath("$[0].lastName",is("Ivanov")))
                .andExpect(jsonPath("$[1].empID",is(2)))
                .andExpect(jsonPath("$[1].lastName",is("Petrov")));
    }

}
