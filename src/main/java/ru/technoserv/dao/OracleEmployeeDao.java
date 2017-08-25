package ru.technoserv.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class OracleEmployeeDao extends JdbcDaoSupport implements EmployeeDao {

    public void create(Employee employee) {

        String sql = "INSERT INTO EMPLOYEE" +
                "(ID, FIRST_NAME, LAST_NAME) VALUES (?,?,?)";

        getJdbcTemplate().update(sql, employee.getId(),
                employee.getFirstName(), employee.getLastName());
    }

    public Employee read(int empID) {

        String sql = "SELECT * FROM EMPLOYEE WHERE ID = ?";

        return (Employee) getJdbcTemplate().queryForObject(sql,
                new Object[]{empID}, new EmployeeRowMapper());
    }

    public void delete(int empID) {
        String sql = "DELETE FROM EMPLOYEE WHERE ID = ?";
        getJdbcTemplate().update(sql, empID);
    }
}
