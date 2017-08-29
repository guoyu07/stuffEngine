package controller;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ru.technoserv.controller.EmployeeController;
import ru.technoserv.dao.Employee;

import javax.servlet.http.HttpServletResponse;


public class EmployeeControllerTest {

    @Mock
    private HttpServletResponse response;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void readParameters() throws  Exception{
       EmployeeController controller = new EmployeeController();
        Assert.assertNotNull(controller.getEmployeeByName("","", response));
    }

    @Test
    public void testHome() throws Exception{
        EmployeeController controller = new EmployeeController();
        Assert.assertEquals("Hello, I'm working!", controller.welcomeMessage());

    }
}
