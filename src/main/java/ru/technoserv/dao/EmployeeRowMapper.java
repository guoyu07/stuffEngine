package ru.technoserv.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setEmpID(resultSet.getInt("EMP_ID"));
        //employee.setPosition((String) resultSet.getObject("TITLE"));
       // employee.setGrade((String) resultSet.getObject("DESCRIPTION"));
      //  employee.setDepartment((String) resultSet.getObject("DEPT_NAME"));
        employee.setFirstName((String) resultSet.getObject("FIRST_NAME"));
        employee.setLastName((String) resultSet.getObject("LAST_NAME"));
        employee.setPatrName((String) resultSet.getObject("PATR_NAME"));
        employee.setGender(resultSet.getString("GENDER").toCharArray()[0]);
        employee.setBirthday(resultSet.getDate("BIRTHDAY"));
        employee.setSalary((BigDecimal) resultSet.getObject("SALARY") );
        return employee;
    }
}
