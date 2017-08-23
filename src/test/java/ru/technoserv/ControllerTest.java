package ru.technoserv;

import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
        org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;
import ru.technoserv.Controller.EmployeeController;


public class ControllerTest {


    @Test
    public void getDefaultPageTest() throws Exception{
        EmployeeController controller = new EmployeeController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/")).andExpect(view().name("employee"));
    }
    @Test
    public void creatingEmployeeTest()throws Exception{
        EmployeeController controller = new EmployeeController();
        MockMvc mockMvc = standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/views/employee.jsp")).build();
        mockMvc.perform(get("/"));
        mockMvc.perform(get("/"));
        mockMvc.perform(get("/"));

    }

}
