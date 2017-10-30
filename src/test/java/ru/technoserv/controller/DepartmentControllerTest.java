package ru.technoserv.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.technoserv.domain.Department;
import ru.technoserv.services.DepartmentService;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.technoserv.util.TestUtils.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DepartmentControllerTest {
    private static GsonHttpMessageConverter converter;
    private static final String JSON_UTF8 = MediaType.APPLICATION_JSON_UTF8_VALUE;

    @Configuration
    static class DepartmentControllerTestConfig {

        @Bean
        DepartmentService departmentService() {
            return mock(DepartmentService.class);
        }

        @Bean
        DepartmentController departmentController() {
            return new DepartmentController();
        }

    }

    @Autowired
    private DepartmentController departmentController;

    @Autowired
    private DepartmentService departmentService;

    private MockMvc mockMvc;
    private Department department;

    @BeforeClass
    public static void beforeClassSetUp() throws Exception {
        converter = new GsonHttpMessageConverter();
        Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().setDateFormat("dd.MM.yyyy").create();
        converter.setGson(gson);
    }

    @Before
    public void setUp() throws Exception {
        Mockito.reset(departmentService);
        department = getNewDep(1, null, "Головной", 1);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).setMessageConverters(converter).build();
    }

    @Test
    public void testGetSubDepts() throws Exception {
        List<Department> subDepts = generateSubDepList();
        OngoingStubbing<List<Department>> stub = when(departmentService.getSubDepts(1)).thenReturn(subDepts);

        ResultActions ra = mockMvc.perform(get("/department/1/subdepts"));
        MvcResult result = ra.andReturn();
        String content = result.getResponse().getContentAsString();
                ra.andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].parentDeptId", is(1)))
                .andExpect(jsonPath("$[0].deptName", is("Закупки")))
                .andExpect(jsonPath("$[0].deptHeadId", isEmptyOrNullString()))
                .andExpect(jsonPath("$[1].id", is(3)))
                .andExpect(jsonPath("$[1].parentDeptId", is(1)))
                .andExpect(jsonPath("$[1].deptName", is("Продажи")))
                .andExpect(jsonPath("$[1].deptHeadId", is(3)))
                .andExpect(jsonPath("$[2].id", is(4)))
                .andExpect(jsonPath("$[2].parentDeptId", is(3)))
                .andExpect(jsonPath("$[2].deptName", is("Аналитика")))
                .andExpect(jsonPath("$[2].deptHeadId", isEmptyOrNullString()));
    }

    @Test
    public void testGetDept() throws Exception {
        when(departmentService.getDepartment(1)).thenReturn(department);
        mockMvc.perform(get("/department/1")).andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("parentDeptId", isEmptyOrNullString()))
                .andExpect(jsonPath("deptName", is("Головной")))
                .andExpect(jsonPath("deptHeadId", is(1)));
    }

    @Test
    public void testCreateDept() throws Exception {
        when(departmentService.createDepartment(any(Department.class))).thenReturn(department);
        Department dep = getNewDep(null, null, "Головной", 1);
        mockMvc.perform(post("/department")
                .contentType(JSON_UTF8)
                .content(convertObjectToJsonBytes(dep))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("parentDeptId", isEmptyOrNullString()))
                .andExpect(jsonPath("deptName", is("Головной")))
                .andExpect(jsonPath("deptHeadId", is(1)));
    }

    @Test
    public void testDeleteDept() throws Exception {
        when(departmentService.deleteDepartment(1)).thenReturn(department);
        mockMvc.perform(delete("/department/1")).andExpect(status().isOk());
    }

    @Test
    public void testUpdateDept() throws Exception {
        Department updatedDep = getNewDep(2, 1, "Закупки", null);
        when(departmentService.updateDept(any(Department.class))).thenReturn(updatedDep);
//        when(departmentService.getParentDept(2)).thenReturn(dep);
        mockMvc.perform(put("/department")
                .contentType(JSON_UTF8)
                .content(convertObjectToJsonBytes(updatedDep))
        )  .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("id", is(2)))
                .andExpect(jsonPath("parentDeptId", is(1)))
                .andExpect(jsonPath("deptName", is("Закупки")))
                .andExpect(jsonPath("deptHeadId", isEmptyOrNullString()));
    }
}
