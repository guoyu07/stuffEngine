package ru.technoserv.dao;

import java.util.List;

public interface DepartmentDao {
    void create(Department department);

    Department read(long id);

    void delete(long id);

    List<Department> getDepartmentsList();
}
