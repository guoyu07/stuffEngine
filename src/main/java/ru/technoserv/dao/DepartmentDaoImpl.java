package ru.technoserv.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    private static final String CREATE_DEPARTMENT =
            "INSERT INTO DEPARTMENT (DEPT_ID, PARENT_DEPT_ID, NAME, DEPT_HEAD_ID) VALUES (?, ?, ?, ?)";
    private static final String SELECT_DEPARTMENT_BY_ID =
            "SELECT DEPT_ID, PARENT_DEPT_ID, NAME, DEPT_HEAD_ID FROM DEPARTMENT WHERE DEPT_ID = ?";
    private static final String DELETE_DEPARTMENT = "DELETE FROM DEPARTMENT WHERE DEPT_ID = ?";
    private static final String SELECT_DEPARTMENTS_LIST =
            "SELECT DEPT_ID, PARENT_DEPT_ID, NAME, DEPT_HEAD_ID FROM DEPARTMENT";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Department department) {
        jdbcTemplate.update(
                CREATE_DEPARTMENT, department.getId(), department.getParentDept(),
                department.getDeptName(), department.getDeptHead());
    }

    @Override
    public Department read(long id) {
        return jdbcTemplate.queryForObject(SELECT_DEPARTMENT_BY_ID, new DepartmentRowMapper(), id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(DELETE_DEPARTMENT, id);
    }

    @Override
    public List<Department> getDepartmentsList() {
        return jdbcTemplate.query(SELECT_DEPARTMENTS_LIST, new DepartmentRowMapper());
    }

    private static class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Department(
                    resultSet.getLong("DEPT_ID"),
                    resultSet.getString("DEPT_NAME")
            );
        }
    }
}