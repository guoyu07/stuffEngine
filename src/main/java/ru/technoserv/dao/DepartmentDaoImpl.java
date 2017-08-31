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
            "INSERT INTO DEPARTMENT (DEPT_ID, PARENT_DEPT_ID, DEPT_NAME, DEPT_HEAD_ID) VALUES (?, ?, ?, ?)";
    private static final String SELECT_DEPARTMENT_BY_ID =
            "SELECT DEPT_ID, PARENT_DEPT_ID, DEPT_NAME, DEPT_HEAD_ID FROM DEPARTMENT WHERE DEPT_ID = ?";
    private static final String DELETE_DEPARTMENT = "DELETE FROM DEPARTMENT WHERE DEPT_ID = ?";
    private static final String SELECT_DEPARTMENTS_LIST =
            "SELECT DEPT_ID, PARENT_DEPT_ID, DEPT_NAME, DEPT_HEAD_ID FROM DEPARTMENT";
    private static final String SELECT_ALL_SUB_DEPTS =
            "SELECT DEPT_ID, PARENT_DEPT_ID, DEPT_NAME, DEPT_HEAD_ID FROM DEPARTMENT START WITH PARENT_DEPT_ID = ? " +
                    "CONNECT BY  PRIOR  DEPT_ID = PARENT_DEPT_ID";
    private static final String SELECT_LEVEL_BELOW_SUB_DEPTS =
            "SELECT DEPT_ID, PARENT_DEPT_ID, DEPT_NAME, DEPT_HEAD_ID FROM DEPARTMENT WHERE PARENT_DEPT_ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Department department) {
        jdbcTemplate.update(
                CREATE_DEPARTMENT, department.getId(), department.getParentDeptId(),
                department.getDeptName(), department.getDeptHeadId());
    }

    @Override
    public Department read(Long depId) {
        return jdbcTemplate.queryForObject(SELECT_DEPARTMENT_BY_ID, new DepartmentRowMapper(), depId);
    }

    @Override
    public void delete(Long depId) {
        jdbcTemplate.update(DELETE_DEPARTMENT, depId);
    }

    @Override
    public List<Department> getDepartmentsList() {
        return jdbcTemplate.query(SELECT_DEPARTMENTS_LIST, new DepartmentRowMapper());
    }

    @Override
    public List<Department> getAllSubDepts(Long depId) {
        return jdbcTemplate.query(SELECT_ALL_SUB_DEPTS, new DepartmentRowMapper(), depId);
    }

    @Override
    public List<Department> getLevelBelowSubDepts(Long depId) {
        return jdbcTemplate.query(SELECT_LEVEL_BELOW_SUB_DEPTS, new DepartmentRowMapper(), depId);
    }

    private static class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Department(
                    resultSet.getLong("DEPT_ID"),
                    (Long) resultSet.getObject("PARENT_DEPT_ID"),
                    resultSet.getString("DEPT_NAME"),
                    (Long) resultSet.getObject("DEPT_HEAD_ID")
            );
        }

    }
}