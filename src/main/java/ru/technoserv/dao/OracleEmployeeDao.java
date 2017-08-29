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
                "(FIRST_NAME, LAST_NAME) VALUES (?,?)";

        jdbcTemplate.update(sql,employee.getFirstName(), employee.getLastName());
    }

    public Employee read(String firstName, String lastName) {

        String sql = "SELECT * FROM EMPLOYEE WHERE FIRST_NAME = ? AND LAST_NAME = ?";

        return  jdbcTemplate.queryForObject(sql,
                new Object[]{firstName, lastName}, new EmployeeRowMapper());
    }

    public void delete(String firstName, String lastName) {
        String sql = "DELETE FROM EMPLOYEE WHERE FIRST_NAME = ? AND LAST_NAME = ?";
        jdbcTemplate.update(sql, firstName, lastName);
    }
}
