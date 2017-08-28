package ru.technoserv.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OracleEmployeeDao implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Employee employee) {

        String sql = "INSERT INTO EMPLOYEE" +
                "(ID, FIRST_NAME, LAST_NAME) VALUES (?,?,?)";

        jdbcTemplate.update(sql, employee.getId(),
                employee.getFirstName(), employee.getLastName());
    }

    public Employee read(int empID) {
        String sql = "SELECT * FROM EMPLOYEE WHERE ID = ?";
        return (Employee) jdbcTemplate.queryForObject(sql,
                new Object[]{empID}, new EmployeeRowMapper());
    }

    public void delete(int empID) {
        String sql = "DELETE FROM EMPLOYEE WHERE ID = ?";
        jdbcTemplate.update(sql, empID);
    }
}
