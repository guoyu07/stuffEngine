package ru.technoserv.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper {

    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("ID"));
        employee.setFirstName(resultSet.getString("FIRST_NAME"));
        employee.setLastName(resultSet.getString("LAST_NAME"));
        return employee;
    }
}
