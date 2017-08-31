package ru.technoserv.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper {

    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setEmpID((Integer) resultSet.getObject("EMP_ID"));
        employee.setPosition((String) resultSet.getObject("TITLE"));
        employee.setGrade((String) resultSet.getObject("DESCRIPTION"));
        employee.setDepartment((String) resultSet.getObject("DEPT_NAME"));
        employee.setFirstName((String) resultSet.getObject("FIRST_NAME"));
        employee.setLastName((String) resultSet.getObject("LAST_NAME"));
        employee.setPatrName((String) resultSet.getObject("PATR_NAME"));
        employee.setGender((Character) resultSet.getObject("GENDER"));
        employee.setBirthday(resultSet.getDate("BIRTHDAY"));
        employee.setSalary((BigDecimal) resultSet.getObject("SALARY") );
        return employee;
    }
}
