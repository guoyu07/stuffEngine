package ru.technoserv.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public class OracleEmployeeDao implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Employee employee) {
        throw new RuntimeException("create() not implemented");
    }

    @Override
    public Employee read(int empID) {
        String sql = "SELECT e.EMP_ID, e.LAST_NAME, e.FIRST_NAME, e.PATR_NAME, e.GENDER, e.BIRTHDAY, e.SALARY, d.DEPT_NAME, p.TITLE, g.DESCRIPTION " +
                "FROM DEPARTMENT d, EMPLOYEE e, POSITION p, GRADE g " +
                "WHERE ((d.DEPT_ID = e.DEPARTMENT_ID) AND (e.EMP_ID = 13) AND (p.POS_ID = e.POSITION_ID) AND (e.GRADE_ID = g.GRD_ID))";
        return (Employee) jdbcTemplate.queryForObject(sql,
                new Object[]{empID}, new EmployeeRowMapper());
    }

    @Override
    public void delete(int empID) {
        String sql = "DELETE FROM EMPLOYEE WHERE EMP_ID = ?";
        jdbcTemplate.update(sql,empID);
    }

    @Override
    public List<Employee> getAllFromDept(String deptName) {
        throw new RuntimeException("getAllFromDept() not implemented");
    }

    @Override
    public void deleteAllFromDept(String deptName) {
        String sql = "SELECT DEPT_ID FROM DEPARTMENT WHERE DEPT_NAME = ?";
        SqlRowSet set = jdbcTemplate.queryForRowSet(sql, deptName);
        set.first();
        int deptID = set.getInt("DEPT_ID");
        sql = "DELETE FROM EMPLOYEE WHERE DEPARTMENT_ID = ?";
        jdbcTemplate.update(sql, deptID);

    }

    @Override
    public void updateDept(int empID, String newDept) {
        String sql = "SELECT DEPT_ID FROM DEPARTMENT WHERE DEPT_NAME = ?";
        SqlRowSet set = jdbcTemplate.queryForRowSet(sql, newDept);
        set.first();
        int newDeptID = set.getInt("DEPT_ID");
        sql = "UPDATE EMPLOYEE SET DEPARTMENT_ID = ? WHERE EMP_ID = ?";
        jdbcTemplate.update(sql, newDeptID, empID);
    }

    @Override
    public void updatePosition(int empID, String newPosition) {
        throw new RuntimeException("updatePosition() not implemented");
    }

    @Override
    public void updateGrade(int empID, String newGrade) {
        throw new RuntimeException("updateGrade() not implemented");
    }

    @Override
    public void updateSalary(int empID, BigDecimal newSalary) {
        String sql = "UPDATE EMPLOYEE SET SALARY = ? WHERE EMP_ID = ?";
        jdbcTemplate.update(sql,newSalary, empID);
    }


//    public void create(Employee employee) {
//
//        String sql = "INSERT INTO EMPLOYEE" +
//                "(FIRST_NAME, LAST_NAME) VALUES (?,?)";
//
//        jdbcTemplate.update(sql,employee.getFirstName(), employee.getLastName());
//    }
//
//    public Employee read(String firstName, String lastName) {
//
//        String sql = "SELECT * FROM EMPLOYEE WHERE FIRST_NAME = ? AND LAST_NAME = ?";
//
//        return (Employee) jdbcTemplate.queryForObject(sql,
//                new Object[]{firstName, lastName}, new EmployeeRowMapper());
//    }
//
//    public void delete(String firstName, String lastName) {
//        String sql = "DELETE FROM EMPLOYEE WHERE FIRST_NAME = ? AND LAST_NAME = ?";
//        jdbcTemplate.update(sql, firstName, lastName);
//    }
}
