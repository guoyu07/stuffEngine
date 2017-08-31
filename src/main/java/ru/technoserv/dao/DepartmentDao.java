package ru.technoserv.dao;

import java.util.List;

public interface DepartmentDao {
    void create(Department department);

    Department read(Long depId);

    void updateParentDeptId(Long newParentDeptId, Long depId);

    void delete(Long depId);

    List<Department> getDepartmentsList();

    List<Department> getAllSubDepts(Long depId);

    List<Department> getLevelBelowSubDepts(Long depId);
}
