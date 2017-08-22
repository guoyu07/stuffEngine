package ru.technoserv;

import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
        org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import ru.technoserv.controllers.EmployeeController;


public class ControllerTest {

    @Test
    public void testGetiingPage()throws Exception{
        EmployeeController controller = new EmployeeController();
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/")).andExpect(view().name("employee"));

    }

    @Test
    public  void getEmployeeInput() throws Exception{
        EmployeeController controller = new EmployeeController();
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/employee?name=Halozy"));//.andExpect(model().attribute("name", "Halozy"));
        Assert.assertEquals("Halozy", controller.name);

    }

}
