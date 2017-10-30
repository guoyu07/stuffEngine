package ru.technoserv.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.technoserv.domain.Department;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static Department getNewDep(Integer id, Integer parentDeptId, String name, Integer deptHeadId) {
        Department dep = new Department();
        dep.setId(id);
        dep.setParentDeptId(parentDeptId);
        dep.setDeptName(name);
        dep.setDeptHeadId(deptHeadId);
        return dep;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(object).getBytes();
    }

    public static List<Department> generateSubDepList() {
        List<Department> departments = new ArrayList<>();

        Department dep1 = new Department();


        departments.add(getNewDep(2, 1, "Закупки", null));
        departments.add(getNewDep(3, 1, "Продажи", 3));
        departments.add(getNewDep(4, 3, "Аналитика", null));

        return departments;
    }
}
