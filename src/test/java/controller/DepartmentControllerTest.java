package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.technoserv.controller.DepartmentController;
import ru.technoserv.domain.Department;
import ru.technoserv.services.DepartmentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DepartmentControllerTest {
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
    private static final String JSON_UTF8 = MediaType.APPLICATION_JSON_UTF8_VALUE;

    @Before
    public void setUp() throws Exception {
        Mockito.reset(departmentService);
        department = getNewDep(1, null, "Головной", 1);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    public void testGetSubDepts() throws Exception {
        List<Department> subDepts = generateSubDepList();
        when(departmentService.getSubDepts(1)).thenReturn(subDepts);
        mockMvc.perform(get("/department/1/subdepts")).andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].parentDeptId", is(1)))
                .andExpect(jsonPath("$[0].deptName", is("Закупки")))
                .andExpect(jsonPath("$[1].id", is(3)))
                .andExpect(jsonPath("$[1].parentDeptId", is(1)))
                .andExpect(jsonPath("$[1].deptName", is("Продажи")))
                .andExpect(jsonPath("$[1].deptHeadId", is(3)))
                .andExpect(jsonPath("$[2].id", is(4)))
                .andExpect(jsonPath("$[2].parentDeptId", is(3)))
                .andExpect(jsonPath("$[2].deptName", is("Аналитика")));
    }

    @Test
    public void testGetDept() throws Exception {
        when(departmentService.getDepartment(1)).thenReturn(department);
        mockMvc.perform(get("/department/1")).andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("id", is(1)))
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
                .andExpect(jsonPath("deptName", is("Головной")))
                .andExpect(jsonPath("deptHeadId", is(1)));
    }

    @Test
    public void testDeleteDept() throws Exception {
        when(departmentService.deleteDepartment(1)).thenReturn(department);
        mockMvc.perform(delete("/department/1")).andExpect(status().isOk())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateDept() throws Exception {
        Department updatedDep = getNewDep(2, 1, "Закупки", null);
        when(departmentService.updateDept(any(Department.class))).thenReturn(updatedDep);
//        when(departmentService.getDepartment(2)).thenReturn(dep);
        mockMvc.perform(put("/department")
                .contentType(JSON_UTF8)
                .content(convertObjectToJsonBytes(updatedDep))
        )  .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("id", is(2)))
                .andExpect(jsonPath("parentDeptId", is(1)))
                .andExpect(jsonPath("deptName", is("Закупки")));
    }

    private List<Department> generateSubDepList() {
        List<Department> departments = new ArrayList<>();

        Department dep1 = new Department();


        departments.add(getNewDep(2, 1, "Закупки", null));
        departments.add(getNewDep(3, 1, "Продажи", 3));
        departments.add(getNewDep(4, 3, "Аналитика", null));

        return departments;
    }

    private Department getNewDep(Integer id, Integer parentDeptId, String name, Integer deptHeadId) {
        Department dep = new Department();
        dep.setId(id);
        dep.setParentDeptId(parentDeptId);
        dep.setDeptName(name);
        dep.setDeptHeadId(deptHeadId);
        return dep;
    }

    private static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(object).getBytes();
    }
}
