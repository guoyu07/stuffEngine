package ru.technoserv.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class OracleEmployeeDao implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Employee employee) {
        String sql = "SELECT d.DEPT_ID, p.POS_ID, g.GRD_ID FROM " +
                "DEPARTMENT d, POSITION p, GRADE g " +
                "WHERE d.DEPT_NAME = ? AND p.TITLE = ? AND g.DESCRIPTION = ?";
        SqlRowSet set = jdbcTemplate.queryForRowSet(sql, employee.getDepartment(),
                employee.getPosition(), employee.getGrade());
        set.first();
        int deptID = set.getInt("DEPT_ID");
        int posID = set.getInt("POS_ID");
        int grdID = set.getInt("GRD_ID");
        sql = "INSERT INTO EMPLOYEE (EMP_ID, LAST_NAME, FIRST_NAME, PATR_NAME, DEPARTMENT_ID, GRADE_ID, POSITION_ID, SALARY, BIRTHDAY, GENDER) VALUES (?,?,?,?,?,?,?,?,?,?) ";
        jdbcTemplate.update(sql, employee.getEmpID(), employee.getLastName(), employee.getFirstName(), employee.getPatrName(), deptID, grdID, posID, employee.getSalary(), employee.getBirthday(), String.valueOf(employee.getGender()));
    }

    @Override
    public Employee read(int empID) {
        String sql = "SELECT e.EMP_ID, e.LAST_NAME, e.FIRST_NAME, e.PATR_NAME, e.GENDER, e.BIRTHDAY, e.SALARY, d.DEPT_NAME, p.TITLE, g.DESCRIPTION " +
                "FROM DEPARTMENT d, EMPLOYEE e, POSITION p, GRADE g " +
                "WHERE ((d.DEPT_ID = e.DEPARTMENT_ID) AND (e.EMP_ID = ?) AND (p.POS_ID = e.POSITION_ID) AND (e.GRADE_ID = g.GRD_ID))";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{empID}, new EmployeeRowMapper());
    }

    @Override
    public void delete(int empID) {
        String sql = "DELETE FROM EMPLOYEE WHERE EMP_ID = ?";
        jdbcTemplate.update(sql, empID);
    }

    @Override
    public List<Employee> getAllFromDept(int deptID) {
        String sql = "SELECT e.EMP_ID, e.LAST_NAME, e.FIRST_NAME, e.PATR_NAME, e.GENDER, e.BIRTHDAY, e.SALARY, d.DEPT_NAME, p.TITLE, g.DESCRIPTION " +
                "FROM EMPLOYEE e, DEPARTMENT d, POSITION p, GRADE g " +
                "WHERE ((e.DEPARTMENT_ID = ?) AND (p.POS_ID = e.POSITION_ID) AND (e.GRADE_ID = g.GRD_ID) AND (e.DEPARTMENT_ID = d.DEPT_ID))";
        return jdbcTemplate.query(sql, new Object[]{deptID}, new EmployeeRowMapper());
    }

    @Override
    public void deleteAllFromDept(int deptID) {
        String sql = "DELETE FROM EMPLOYEE WHERE DEPARTMENT_ID = ?";
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
        String sql = "SELECT POS_ID FROM POSITION WHERE TITLE = ?";
        SqlRowSet set = jdbcTemplate.queryForRowSet(sql, newPosition);
        set.first();
        int newPosID = set.getInt("POS_ID");
        sql = "UPDATE EMPLOYEE SET POSITION_ID = ? WHERE EMP_ID = ?";
        jdbcTemplate.update(sql, newPosID, empID);
    }

    @Override
    public void updateGrade(int empID, String newGrade) {
        String sql = "SELECT GRD_ID FROM GRADE WHERE DESCRIPTION = ?";
        SqlRowSet set = jdbcTemplate.queryForRowSet(sql, newGrade);
        set.first();
        int newGrdID = set.getInt("GRD_ID");
        sql = "UPDATE EMPLOYEE SET GRADE_ID = ? WHERE EMP_ID = ?";
        jdbcTemplate.update(sql, newGrdID, empID);
    }

    @Override
    public void updateSalary(int empID, BigDecimal newSalary) {
        String sql = "UPDATE EMPLOYEE SET SALARY = ? WHERE EMP_ID = ?";
        jdbcTemplate.update(sql, newSalary, empID);
    }

}
